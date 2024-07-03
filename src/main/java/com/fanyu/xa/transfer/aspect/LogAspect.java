package com.fanyu.xa.transfer.aspect;

import com.fanyu.xa.transfer.controller.GhCallController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static Logger log = LoggerFactory.getLogger(GhCallController.class);

    @Pointcut("execution(* com.fanyu.xa.transfer.controller.GhCallController.*(..))")
    public void tellHowCallLog() {
    }

    @Around("tellHowCallLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            log.info("泰豪调用：{}服务，请求数据：{}", joinPoint.getSignature().getName(), joinPoint.getArgs());
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("日志记录拦截器调用方法出错", e);
        }
        log.info("泰豪调用：{}服务，请求数据：{}，响应数据：{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), result);
        return result;
    }
}
