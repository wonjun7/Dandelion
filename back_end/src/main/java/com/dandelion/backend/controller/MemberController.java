package com.dandelion.backend.controller;


import com.dandelion.backend.entity.form.MemberModifyForm;
import com.dandelion.backend.entity.form.PasswordModifyForm;
import com.dandelion.backend.entity.member.Member;
import com.dandelion.backend.entity.member.MyMemberDetails;
import com.dandelion.backend.service.MemberService;
import com.dandelion.backend.service.MemberServiceImpl;
import com.dandelion.backend.utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Log4j2
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping(value = "/signUp")
    public ResponseEntity signUp(@RequestBody Member member) {
        member.setPw(passwordEncoder.encode(member.getPw()));
        return memberService.findById(member.getId()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(memberService.signUp(member)));
    }
    @RequestMapping(value = "/member/view", method = RequestMethod.GET)
    public Optional<Member> memberView(@AuthenticationPrincipal MyMemberDetails myMemberDetails){
        Member member = myMemberDetails.getMember();
        return memberService.findById(member.getId());
    }


    @PostMapping(value = "/member/modify/myProfile")
    public Member modifiyMember(@RequestBody Member member, @AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        Member modifyMember = memberService.findById(myMemberDetails.getId()).get();
        modifyMember.setName(member.getName());
        modifyMember.setPhone(member.getPhone());
        modifyMember.setAddress(member.getAddress());

        return memberService.save(modifyMember);
    }


    @PostMapping(value = "/member/modify/password")
    public Map<String, Integer> memberModify(@AuthenticationPrincipal MyMemberDetails myMemberDetails, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 회원정보 수정 요청시 받는 폼
        PasswordModifyForm passwordModifyForm =  new ObjectMapper().readValue(request.getInputStream(), PasswordModifyForm.class);
        Map<String, Integer> returnJson = new HashMap<>();

        // 기존 비밀번호와 수정 폼에서 입력한 비밀번호가 일치할시 1을 반환하고 회원정보 업데이트
        if(passwordEncoder.matches(passwordModifyForm.getCurrent_password(), myMemberDetails.getPw())) {
            passwordModifyForm.setNew_password(passwordEncoder.encode(passwordModifyForm.getNew_password()));
            memberService.passwordUpdate(myMemberDetails.getMember(), passwordModifyForm);

            returnJson.put("return", 1);
            return returnJson;
        }

        // 기존 비밀번호와 수정폼에서 입력한 비밀번호가 불일치시 error로 0을 반환
        returnJson.put("return", 0);
        return returnJson;
    }


    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping(value = "/pointCharge")
    public Member pointCharge (@AuthenticationPrincipal MyMemberDetails myMemberDetails, @RequestBody Map<String, Integer> point ) {
        Member member = memberService.getOne(myMemberDetails.getNumber());
        member.setPoint(member.getPoint()+ point.get("result"));
        return memberService.save(member);
    }
}
