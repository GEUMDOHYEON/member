package com.example.member.member.service;

import com.example.member.member.entity.Member;
import com.example.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void register(Member member) {
        memberRepository.save(member);
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }
}
