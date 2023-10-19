package com.study.train.member.service;

import com.study.train.member.req.MemberLoginReq;
import com.study.train.member.req.MemberRegisterReq;
import com.study.train.member.req.MemberSendCodeReq;
import com.study.train.member.resp.MemberLoginResp;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
    void sendCode(MemberSendCodeReq req);
    MemberLoginResp Login(MemberLoginReq req);
}
