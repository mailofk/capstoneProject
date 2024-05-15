package com.capstone.member;

public interface MemberRepository {
    void save();

    Member findById(Long memberId);

}
