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
 * TellHowCallController 调用泰豪的接口
 *
 * @author lishijian
 * @version demo 1.0.0.RELEASE
 * <b>Creation Time:</b> 2024/6/27 10:26
 */
@Slf4j
@RestController
@RequestMapping("/tell-how")
public class TellHowCallController {

    @Autowired
    private BasicCallService basicCallService;

    /**
     * 通话状态上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/callevent")
    public AsynResponse callCallevent(@RequestBody AsynRequest asynRequest){

        log.info("callCallevent 接口收到参数：" + asynRequest.toString());
        return basicCallService.callCallevent(asynRequest);
    }

    /**
     * 按键事件（dtmf）上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/dtmfevent")
    public AsynResponse callDtmfevent(@RequestBody AsynRequest asynRequest){

        log.info("callDtmfevent 接口收到参数：" + asynRequest.toString());
        return basicCallService.callDtmfevent(asynRequest);
    }

    /**
     * 翻译文本上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/sendtext")
    public AsynResponse callSendtext(@RequestBody AsynRequest asynRequest){

        log.info("callSendtext 接口收到参数：" + asynRequest.toString());
        return basicCallService.callSendtext(asynRequest);
    }


    /**
     * 呼叫录音链接上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/call/recordaddr")
    public AsynResponse callRecordaddr(@RequestBody AsynRequest asynRequest){

        log.info("callRecordaddr 接口收到参数：" + asynRequest.toString());
        return basicCallService.callRecordaddr(asynRequest);
    }


    /**
     * 广播成员状态上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/broadcast/memberstatus")
    public AsynResponse broadcastMemberstatus(@RequestBody AsynRequest asynRequest){

        log.info("broadcastMemberstatus 接口收到参数：" + asynRequest.toString());
        return basicCallService.broadcastMemberstatus(asynRequest);
    }

    /**
     * 广播结束状态上报
     * @param asynRequest
     * @return
     */
    @PostMapping(value = "/broadcast/bcstatus")
    public AsynResponse broadcastBcstatus(@RequestBody AsynRequest asynRequest){

        log.info("broadcastBcstatus 接口收到参数：" + asynRequest.toString());
        return basicCallService.broadcastBcstatus(asynRequest);
    }
}
