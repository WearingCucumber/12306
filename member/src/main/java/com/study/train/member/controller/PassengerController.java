package com.study.train.member.controller;

import cn.hutool.core.bean.BeanUtil;
import com.study.train.common.resp.CommonResp;
import com.study.train.member.domain.Passenger;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.resp.PassengerQueryResp;
import com.study.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @PostMapping("/save")
    public CommonResp save(@RequestBody @Valid PassengerSaveReq passengerSaveReq){
        passengerService.save(passengerSaveReq);
        return new CommonResp("添加成功");
    }
    @GetMapping("/query-list")
    public CommonResp<List<PassengerQueryResp>> queryList(PassengerQueryReq passengerQueryReq){
//        PassengerQueryReq passengerQueryReq = new PassengerQueryReq();
//        passengerQueryReq.setMemberId(LoginMemberContext.getId());
        List<Passenger> passengers = passengerService.queryList(passengerQueryReq);
        List<PassengerQueryResp> resps = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        return new CommonResp<>(resps);
    }
}
