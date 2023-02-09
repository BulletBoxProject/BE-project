package com.hanghae.bulletbox.member.repository;

import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

}