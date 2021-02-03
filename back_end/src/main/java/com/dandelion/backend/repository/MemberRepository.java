package com.dandelion.backend.repository;

import com.dandelion.backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByIdAndPw(String id, String pw);

    Optional<Member> findById(String id);

}
