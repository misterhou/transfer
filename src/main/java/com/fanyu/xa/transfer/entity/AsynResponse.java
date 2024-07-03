package com.fanyu.xa.transfer.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * AsynResponse Description
 */
@Data
@Builder
public class AsynResponse{

    /**
     * 请求ID（唯一），响应时带回
     */
    private String reqId;

    /**
     * 响应数据
     */
    private Map<String, Object> data;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 错误码
     */
    private String errorCode;
}
