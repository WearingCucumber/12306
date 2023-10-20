package com.study.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.study.train.common.exception.BusinessException;
import com.study.train.common.exception.BusinessExceptionEnum;
import com.study.train.common.util.JwtUtil;
import com.study.train.common.util.SnowUtil;
import com.study.train.member.domain.Member;
import com.study.train.member.domain.MemberExample;
import com.study.train.member.mapper.MemberMapper;
import com.study.train.member.req.MemberLoginReq;
import com.study.train.member.req.MemberRegisterReq;
import com.study.train.member.req.MemberSendCodeReq;
import com.study.train.member.resp.MemberLoginResp;
import com.study.train.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));

    }

    @Override
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        Member member= selectByMobile(mobile);
        if (ObjectUtil.isNotNull(member))
//            return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);

        Member newMember = new Member();
        newMember.setId(SnowUtil.getSnowflakeNextId());
        newMember.setMobile(mobile);
        memberMapper.insert(newMember);
        return newMember.getId();

    }

    @Override
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member member =  selectByMobile(mobile);
        if (ObjectUtil.isNull(member)){
            Member newMember = new Member();
            newMember.setId(SnowUtil.getSnowflakeNextId());
            newMember.setMobile(mobile);
            memberMapper.insert(newMember);
        }
        //生成验证码
        String code = RandomUtil.randomString(6);
        log.info(code);
        //保存信息记录表： 手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        //对接短信通道，发送短信


    }
    @Override
    public MemberLoginResp Login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member member = selectByMobile(mobile);
        if (ObjectUtil.isNull(member)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        //校验短信验证码
        if (!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }else {
            MemberLoginResp resp = BeanUtil.copyProperties(member, MemberLoginResp.class);
            String token = JwtUtil.createToken(resp.getId(), resp.getMobile());
            resp.setToken(token);
            return resp;

        }


    }
    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)){
            return null;
        }else {
            return list.get(0);
        }
    }
}
