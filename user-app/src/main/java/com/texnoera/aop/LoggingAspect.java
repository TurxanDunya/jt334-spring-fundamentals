package com.texnoera.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Before("springBeanPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName()
                + " started with args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("springBeanPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " ended");
    }


//    @Around("springBeanPointcut()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        return joinPoint.proceed();
//    }

}