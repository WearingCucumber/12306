package com.study.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.train.common.context.LoginMemberContext;
import com.study.train.common.resp.PageResp;
import com.study.train.common.util.SnowUtil;
import com.study.train.member.domain.Passenger;
import com.study.train.member.mapper.PassengerMapper;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.resp.PassengerQueryResp;
import com.study.train.member.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public void save(PassengerSaveReq passengerSaveReq) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(passengerSaveReq, Passenger.class);
        log.info("LoginMemberContext:{}",LoginMemberContext.getMember());
        if (ObjectUtil.isNull(passenger.getId())){
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        }else {
            passenger.setUpdateTime(now);
            passengerMapper.updateById(passenger);

        }


    }

    @Override
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PageHelper.startPage(req.getPage(),req.getPageSize());
        List<Passenger> passengers = passengerMapper.queryList(req);
        PageInfo<Passenger> passengerPageInfo = new PageInfo<>(passengers);
        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> passengerPageResp = new PageResp<>();
        passengerPageResp.setTotal(passengerPageInfo.getTotal());
        passengerPageResp.setList(passengerQueryResps);
        return passengerPageResp;
    }

    @Override
    public void delete(Long id) {
        passengerMapper.deleteById(id);
    }
}
