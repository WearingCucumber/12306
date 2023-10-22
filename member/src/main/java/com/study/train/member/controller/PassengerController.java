package com.study.train.member.controller;

import com.study.train.common.resp.CommonResp;
import com.study.train.common.resp.PageResp;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.resp.PassengerQueryResp;
import com.study.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
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
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Validated PassengerQueryReq passengerQueryReq){
        PageResp<PassengerQueryResp> list = passengerService.queryList(passengerQueryReq);
        return new CommonResp<>(list);
    }
}
