package com.fanyu.xa.transfer.entity;

import lombok.Data;

/**
 * 请求参数实体
 */
@Data
public class RequestPar {

    //请求ID（唯一），响应时带回
    private String reqid;

    //呼叫类型
    private String type;

    // 发起呼叫返回通话唯一callid
    private String callid;

    //主叫号码（拨打电话侧）
    private String clgnum;

    //被叫号码（接听电话侧）
    private String cldnum;

    //开场白[可选，最大1024字节，UTF-8编码]
    private String text;

    //开场白[可选，最大1024字节，UTF-8编码]
    private Object params;

    //第三方认证使用(可选)
    private String token;

    //第三方认证使用(可选)
    private String appId;

    // 呼叫状态参考下表
    private String state;

    // 主叫站点名(可选不关心)
    private String clgstation;

    // 主叫用户名(可选不关心)
    private String clgname;

    // 被叫站点名(可选不关心)
    private String cldstation;

    // 被叫用户名(可选不关心)
    private String cldname;

    // 主被叫类型参考下表
    private String calltype;

    // 按下按键编号
    private String digit;

    // 主被叫类型参考下表
    private String vprinfo;

    // 按下按键编号
    private String timestamp;

    // 按下按键编号（http://xxx/audio/20200518/xxx.wav）
    private String address;

    // 广播通知文本，最多340个汉字（utf-8）
    private String contents;

    // 广播主叫号码
    private String clgnumber;

    // 具体被叫成员号码(最多100个成员，格式“xxx;xxxx;xxxx”)
    private String memberinfo;

    // 是否需要反馈，1---需要, 0---不需要
    private String needres;

    // 1~7 (天)；指定天数内任务反馈
    private String resday;

    // 发起广播时返回的广播ID
    private String bdid;

    // 增加成员的总数
    private String membercount;

    // 广播被叫成员号码
    private String member;

    // 状态参考下表
    private String status;

    // 错误码
    private String errcode;
}
