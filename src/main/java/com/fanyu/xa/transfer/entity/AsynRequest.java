package com.fanyu.xa.transfer.entity;

import lombok.Data;

import java.util.Map;

/**
 * 扩展类
 */
@Data
public class AsynRequest{

    /**
     * 请求 id
     */
    private String reqId;

    /**
     * 业务数据
     */
    private Map<String,Object> data;

    /**
     * 请求时间戳
     */
    private String timestamp;

}
