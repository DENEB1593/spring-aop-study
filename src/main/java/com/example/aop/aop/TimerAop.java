package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {}

    // controller 하위에 Timer 어노테이션이 설정된 메소드만 AOP 적용
    @Around("cut() && enableTimer()")
    public void aroud(ProceedingJoinPoint joinPoint) throws Throwable {
        // 실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Object result = joinPoint.proceed();

        // 실행 후
        stopWatch.stop();
        System.out.println("total elapsed time : "  + stopWatch.getTotalTimeSeconds());

    }


}
