package com.study.train.member.controller;

import cn.hutool.core.util.ObjectUtil;
import com.study.train.common.resp.CommonResp;
import com.study.train.common.resp.PageResp;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.resp.PassengerQueryResp;
import com.study.train.member.service.PassengerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
        if (ObjectUtil.isNotNull(passengerSaveReq.getId())){
            return new CommonResp("更新成功");
        }
        return new CommonResp("添加成功");
    }
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Validated PassengerQueryReq passengerQueryReq){
        PageResp<PassengerQueryResp> list = passengerService.queryList(passengerQueryReq);
        return new CommonResp<>(list);
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@NotNull(message = "id不能为空") @PathVariable Long id){
        passengerService.delete(id);
        return new CommonResp("删除成功");
    }

}
