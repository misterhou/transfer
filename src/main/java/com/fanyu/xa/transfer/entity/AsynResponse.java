package com.fanyu.xa.transfer.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    public static AsynResponse notFound() {
        AsynResponse asynResponse = AsynResponse.builder().build();
        asynResponse.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        asynResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return asynResponse;
    }

    public static AsynResponse serviceError() {
        AsynResponse asynResponse = AsynResponse.builder().build();
        asynResponse.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        asynResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return asynResponse;
    }
}
