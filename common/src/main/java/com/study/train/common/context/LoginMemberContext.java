package com.study.train.common.context;

import com.study.train.common.resp.MemberLoginResp;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginMemberContext {
    private  static ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static ThreadLocal<MemberLoginResp> getMember() {
        return member;
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.member.set(member);
    }
    public static Long  getId(){
       try {
           return member.get().getId();
       }catch (Exception e){
           log.error("获取登录会员信息异常",e);
           throw  e;
       }
    }
}
