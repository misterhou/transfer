package com.fanyu.xa.transfer.controller;

import com.fanyu.xa.transfer.entity.AsynRequest;
import com.fanyu.xa.transfer.entity.AsynResponse;
import com.fanyu.xa.transfer.service.BasicCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GhCallController 调用广哈接口
 *
 * @author lishijian
 * @version demo 1.0.0.RELEASE
 * <b>Creation Time:</b> 2024/6/27 10:26
 */
@Slf4j
@RestController
@RequestMapping("/gh")
public class GhCallController {

    @Autowired
    private BasicCallService basicCallService;

    /**
     * 发起呼叫
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/startcall")
    public AsynResponse callStartcall(@RequestBody AsynRequest asynRequest){
        AsynResponse asynResponse = basicCallService.callStartcall(asynRequest);
        return asynResponse;
    }

    /**
     * 接通电话
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/answercall")
    public AsynResponse callAnswercall(@RequestBody AsynRequest asynRequest){
        return basicCallService.callAnswercall(asynRequest);
    }

    /**
     * 关闭呼叫
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/releasecall")
    public AsynResponse callReleasecall(@RequestBody AsynRequest asynRequest){
        return basicCallService.callReleasecall(asynRequest);
    }

    /**
     * 呼叫控制
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/callcontrol")
    public AsynResponse callCallcontrol(@RequestBody AsynRequest asynRequest){
        return basicCallService.callCallcontrol(asynRequest);
    }

    /**
     * 通知文本下发
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/textmsg")
    public AsynResponse callTextmsg(@RequestBody AsynRequest asynRequest){
        return basicCallService.callTextmsg(asynRequest);
    }


    /**
     * 发起广播
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/broadcast/startbc")
    public AsynResponse broadcastStartbc(@RequestBody AsynRequest asynRequest){
        return basicCallService.broadcastStartbc(asynRequest);
    }

    /**
     * 增加呼叫成员
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/broadcast/addmember")
    public AsynResponse broadcastAddmember(@RequestBody AsynRequest asynRequest){
        return basicCallService.broadcastAddmember(asynRequest);
    }
}
