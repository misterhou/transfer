package com.fanyu.xa.transfer.service;

import com.fanyu.xa.transfer.entity.AsynRequest;
import com.fanyu.xa.transfer.entity.AsynResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 *
 */
@Slf4j
@Service
public class BasicCallService {

    @Value("${uri.call.tell-how}")
    private String uriCallTellHow;

    @Value("${uri.call.gh}")
    private String uriCallGh;

    //发起呼叫
    public AsynResponse callStartcall(AsynRequest asynRequest) {
        String url = uriCallGh + "/call/startcall";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //接听通话
    public AsynResponse callAnswercall(AsynRequest asynRequest) {
        String url = uriCallGh + "/call/answercall";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //关闭呼叫
    public AsynResponse callReleasecall(AsynRequest asynRequest) {
        String url = uriCallGh + "/call/releasecall";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //呼叫控制
    public AsynResponse callCallcontrol(AsynRequest asynRequest) {
        String url = uriCallGh + "/call/callcontrol";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //通话状态上报
    public AsynResponse callCallevent(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/call/callevent";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //按键事件（dtmf）上报
    public AsynResponse callDtmfevent(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/call/dtmfevent";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //通知文本下发
    public AsynResponse callTextmsg(AsynRequest asynRequest) {
        String url = uriCallGh + "/call/textmsg";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //翻译文本上报
    public AsynResponse callSendtext(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/call/sendtext";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //呼叫录音链接上报
    public AsynResponse callRecordaddr(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/call/recordaddr";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //发起广播
    public AsynResponse broadcastStartbc(AsynRequest asynRequest) {
        String url = uriCallGh + "/broadcast/startbc";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //增加呼叫成员
    public AsynResponse broadcastAddmember(AsynRequest asynRequest) {
        String url = uriCallGh + "/broadcast/addmember";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //广播成员状态上报
    public AsynResponse broadcastMemberstatus(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/broadcast/memberstatus";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }

    //广播结束状态上报
    public AsynResponse broadcastBcstatus(AsynRequest asynRequest) {
        String url = uriCallTellHow + "/broadcast/bcstatus";
        AsynResponse asynResponse = forward(url, asynRequest.getData());
        asynResponse.setReqId(asynRequest.getReqId());
        return asynResponse;
    }


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * 发起呼叫
     *
     * @return
     */
    public AsynResponse forward(String url, Map<String,Object> data) {

        //新建ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();
        //使用ObjectMapper对象对 User对象进行转换
        String json = null;
        try {
            json = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("调用广哈服务：{}，请求参数：{}", url, json);

        //OK调用
        OkHttpClient client = new OkHttpClient();

        okhttp3.RequestBody body = okhttp3.RequestBody.create(json, JSON);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();

        Map<String, Object> map = null;
        String message = "9999";
        Response response = null;
        try{
            response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // 获取响应体
            String jsonString = response.body().string();
            map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
            log.info("接收到广哈的响应数据: {}" , map.toString());
        } catch (IOException e) {
            log.error("请求广哈服务异常", e);
        }
        if (null != response) {
            message = response.code() + "";
        }
        return AsynResponse.builder()
                .data(map)
                .errorCode(message).timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
