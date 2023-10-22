package com.study.train.member.controller;

import com.study.train.common.resp.CommonResp;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
