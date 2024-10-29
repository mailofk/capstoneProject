package com.capstone.capstoneProject.repository;

import com.capstone.capstoneProject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
