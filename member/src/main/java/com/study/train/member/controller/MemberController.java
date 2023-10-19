package com.study.train.member.controller;

import com.study.train.common.resp.CommonResp;
import com.study.train.member.req.MemberLoginReq;
import com.study.train.member.req.MemberRegisterReq;
import com.study.train.member.req.MemberSendCodeReq;
import com.study.train.member.resp.MemberLoginResp;
import com.study.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")

public class MemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/count")
    public CommonResp<Integer> count(){
        CommonResp<Integer> com = new CommonResp<>();
        int count = memberService.count();
        com.setContent(count);
        return com;
    }
    @PostMapping("/register")
    public CommonResp<Long> register(@Validated MemberRegisterReq req){
//        CommonResp<Long> com = new CommonResp<>();
//        long register = memberService.register(req);
//        com.setContent(register);
//        return com ;
        return new CommonResp<>(memberService.register(req));
    }
    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@RequestBody @Valid MemberSendCodeReq req){
        memberService.sendCode(req);
        return  new CommonResp<>();
    }
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@RequestBody @Valid MemberLoginReq req){
        MemberLoginResp resp = memberService.Login(req);
        return  new CommonResp<>(resp);
    }

}
