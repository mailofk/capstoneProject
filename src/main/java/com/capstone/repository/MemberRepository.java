package com.capstone.repository;

import com.capstone.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    void save();

}
