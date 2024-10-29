package com.capstone.capstoneProject.service;


import com.capstone.capstoneProject.domain.Member;
import com.capstone.capstoneProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(String password, String email) {
        Member member = new Member(password, email);
        memberRepository.save(member);
    }
}
