package com.study.train.member.service;

import com.study.train.member.req.MemberRegisterReq;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
}
