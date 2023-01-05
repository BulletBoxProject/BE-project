package com.hanghae.bulletbox.member.repository;

import com.hanghae.bulletbox.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
