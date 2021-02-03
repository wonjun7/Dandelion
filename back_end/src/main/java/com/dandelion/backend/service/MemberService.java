package com.dandelion.backend.service;


import com.dandelion.backend.entity.form.MemberModifyForm;
import com.dandelion.backend.entity.form.PasswordModifyForm;
import com.dandelion.backend.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member signUp(Member user);

    Optional<Member> findById(String id);

    //    findByPoint(String id);
    List<Member> findAll();

    Member save (Member member);

    Member passwordUpdate(Member member, PasswordModifyForm passwordModifyForm);

    Member getOne(int number);
}
