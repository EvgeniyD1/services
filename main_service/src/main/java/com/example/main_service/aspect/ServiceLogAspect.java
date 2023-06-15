package com.example.main_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class ServiceLogAspect {

    @Pointcut("execution(* com.example.main_service.service..*(..))")
    public void serviceLog() {
    }

    @Around("serviceLog()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch sw = new StopWatch();
        log.info("Method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName()
                + " start");
        sw.start();
        Object proceed = joinPoint.proceed();
        sw.stop();
        log.info("Method " + joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName()
                + " finished. Method execution time: " + sw.getTotalTimeMillis() + " milliseconds.");
        return proceed;
    }
}
