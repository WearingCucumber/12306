package com.study.train.member.controller;

import com.study.train.common.resp.CommonResp;
import com.study.train.member.req.MemberRegisterReq;
import com.study.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResp<Long> register(MemberRegisterReq req){
//        CommonResp<Long> com = new CommonResp<>();
//        long register = memberService.register(req);
//        com.setContent(register);
//        return com ;
        return new CommonResp<>(memberService.register(req));
    }
}
