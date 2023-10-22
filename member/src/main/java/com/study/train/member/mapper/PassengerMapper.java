package com.study.train.member.mapper;

import com.study.train.member.domain.Passenger;
import com.study.train.member.req.PassengerQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PassengerMapper {

    void insert(Passenger passenger);

    List<Passenger> queryList(PassengerQueryReq passengerQueryReq);
    void updateById(Passenger passenger);
}