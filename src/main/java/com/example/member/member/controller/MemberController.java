package com.example.member.member.controller;

import com.example.member.member.entity.Member;
import com.example.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member member) {
        memberService.register(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");
    }

    @GetMapping()
    public ResponseEntity<Member> getMember(@RequestParam String name) {
        Member member = memberService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
