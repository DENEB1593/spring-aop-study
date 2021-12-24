package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    // 어느부분에 AOP를 적용할지를 정의
    // controller 내부의 모든 메소드에 모두 적용하겠다.
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {

    }
    
    // cut 실행 전
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method name : " + method.getName());

        Object[] args = joinPoint.getArgs();        // 매개변수

        for (Object arg : args) {
            System.out.println("type : " + arg.getClass().getSimpleName());
            System.out.println("value : " + arg);
        }
    }

    // cut 실행 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void after(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj : " + returnObj);
    }

}
