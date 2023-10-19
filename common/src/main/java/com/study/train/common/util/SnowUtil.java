package com.study.train.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowUtil {
    private static  long dataCenterId = 1;
    private static  long workerId = 1;
    public static long getSnowflakeNextId(){
        return IdUtil.getSnowflake(dataCenterId,workerId).nextId();
    }
    public static String getSnowflakeNextIdStr(){
        return IdUtil.getSnowflake(dataCenterId,workerId).nextIdStr();
    }

}
