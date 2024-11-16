package com.capstone.capstoneProject.controller;

import com.capstone.capstoneProject.dto.ApiSuccessForm;
import com.capstone.capstoneProject.dto.JoinForm;
import com.capstone.capstoneProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final MemberService memberService;

    @PostMapping("/api/join")
    public ApiSuccessForm joinMember(@RequestBody JoinForm joinForm) {

        ApiSuccessForm apiSuccessForm = new ApiSuccessForm();
        apiSuccessForm.setResult(true);

        if (memberService.checkDuplicate(joinForm.getEmail())) {
            apiSuccessForm.setResult(false);
        }
        if (apiSuccessForm.isResult()) {
            memberService.save(joinForm);
        }

        return apiSuccessForm;
    }

//    @PostMapping("/api/login")
//    public void loginMember(@RequestBody JoinForm joinForm) {
//
//    }

}


