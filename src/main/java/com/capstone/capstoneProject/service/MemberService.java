package com.capstone.capstoneProject.service;


import com.capstone.capstoneProject.domain.Member;
import com.capstone.capstoneProject.domain.Role;
import com.capstone.capstoneProject.dto.JoinForm;
import com.capstone.capstoneProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(String email, Role role) {
        Member member = new Member(email, role);
        memberRepository.save(member);
    }

    public Member save(JoinForm joinForm) {
        Member member = new Member(joinForm.getEmail(), joinForm.getRole());

        return memberRepository.save(member);
    }

    public boolean checkDuplicate(String id) {
        return memberRepository.existsByEmail(id);
    }
}
