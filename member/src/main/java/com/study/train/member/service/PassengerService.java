package com.study.train.member.service;

import com.study.train.common.resp.PageResp;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;
import com.study.train.member.resp.PassengerQueryResp;

public interface PassengerService {
    void save(PassengerSaveReq passengerSaveReq);
    PageResp<PassengerQueryResp> queryList(PassengerQueryReq req);

    void delete(Long id);
}
