package com.jpaloadmap.repository;

import com.jpaloadmap.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
