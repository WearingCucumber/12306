package com.study.train.member.service;

import com.study.train.member.domain.Passenger;
import com.study.train.member.req.PassengerQueryReq;
import com.study.train.member.req.PassengerSaveReq;

import java.util.List;

public interface PassengerService {
    void save(PassengerSaveReq passengerSaveReq);
    List<Passenger> queryList(PassengerQueryReq req);
}
