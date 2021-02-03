package com.dandelion.backend.service;

import com.dandelion.backend.entity.form.MemberModifyForm;
import com.dandelion.backend.entity.form.PasswordModifyForm;
import com.dandelion.backend.entity.member.Member;
import com.dandelion.backend.repository.MemberRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{


    @NonNull
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member signUp(Member user) {
        return memberRepository.save(user);
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member passwordUpdate(Member member, PasswordModifyForm passwordModifyForm) {
        member.setPw(passwordModifyForm.getNew_password());

        return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save (Member member)  {
        return memberRepository.save(member);
    }

    @Override
    public Member getOne(int number) {
        return memberRepository.getOne(number);
    }
}
