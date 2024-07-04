package com.fanyu.xa.transfer.controller;

import com.fanyu.xa.transfer.entity.AsynRequest;
import com.fanyu.xa.transfer.entity.RequestPar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  Description
 *
 * @author lishijian
 */
@RestController
public class TransferController {

    /**
     * 发起呼叫测试
     * @return
     */
    @PostMapping(value = "/call/startcall")
    public RequestPar saveComment(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 接通电话测试
     * @return
     */
    @PostMapping(value = "/call/answercall")
    public RequestPar callAnswercall(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 关闭呼叫测试
     * @return
     */
    @PostMapping(value = "/call/releasecall")
    public RequestPar callReleasecall(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 呼叫控制测试
     * @return
     */
    @PostMapping(value = "/call/callcontrol")
    public RequestPar callCallcontrol(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 通话状态上报测试
     * @return
     */
    @PostMapping(value = "/call/callevent")
    public RequestPar callCallevent(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getTellHowResponse();
    }

    /**
     * 按键事件（dtmf）上报测试
     * @return
     */
    @PostMapping(value = "/call/dtmfevent")
    public RequestPar callDtmfevent(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getTellHowResponse();
    }

    /**
     * 通知文本下发测试
     * @return
     */
    @PostMapping(value = "/call/textmsg")
    public RequestPar callTextmsg(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 翻译文本上报测试
     * @return
     */
    @PostMapping(value = "/call/sendtext")
    public RequestPar callSendtext(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 呼叫录音链接上报测试
     * @return
     */
    @PostMapping(value = "/call/recordaddr")
    public RequestPar callRecordaddr(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getTellHowResponse();
    }

    /**
     * 发起广播测试
     * @return
     */
    @PostMapping(value = "/broadcast/startbc")
    public RequestPar broadcastStartbc(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 增加呼叫成员测试
     * @return
     */
    @PostMapping(value = "/broadcast/addmember")
    public RequestPar broadcastAddmember(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getGhResponse();
    }

    /**
     * 广播成员状态上报测试
     * @return
     */
    @PostMapping(value = "/broadcast/memberstatus")
    public RequestPar broadcastMemberstatus(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getTellHowResponse();
    }

    /**
     * 广播结束状态上报测试
     * @return
     */
    @PostMapping(value = "/broadcast/bcstatus")
    public RequestPar broadcastBcstatus(@RequestBody AsynRequest requestPar){

        System.out.println(requestPar.toString());
        return getTellHowResponse();
    }



    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/json-string")
    public String handleJsonStringRequest(@RequestBody String jsonString)  {
        try {
            // 将 JSON 字符串解析为 JsonNode 对象
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println("Received JSON: " + jsonNode.toString());

            // 访问 JSON 字段
            String reqid = jsonNode.get("reqid").asText();
            System.out.println("Value for 'reqid': " + reqid);

            // 访问 JSON 字段
            JsonNode params = jsonNode.get("params");
            System.out.println("Value for 'params': " + params.get("aaa").asText());

            // 返回响应
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing JSON";
        }
    }


    /**
     * 测试
     * @param username
     * @return
     */
    @GetMapping(value = "/test")
    public RequestPar saveComment(@RequestParam(name = "username") String username){

        System.out.println(username);

        return null;
    }

    @NotNull
    private static RequestPar getGhResponse() {
        RequestPar response = new RequestPar();
        response.setType("gh");
        response.setErrorCode("ok");
        return response;
    }

    private static RequestPar getTellHowResponse() {
        RequestPar response = new RequestPar();
        response.setType("tell-how");
        response.setErrorCode("ok");
        return response;
    }

}

