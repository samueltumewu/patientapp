package com.samuel.patientapp.annotations.classmethodtiming;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectClassMethodTiming {
    @Pointcut("within(@ClassMethodTiming *)")
    public void classMethodTimingPointCut(){}

    @Around(value = "classMethodTimingPointCut()")
    public Object adviceAroundClassMethodTiming(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("adviceAroundClassMethodTiming throwable: {}", e.getLocalizedMessage());
        } finally {
            long execTiming = System.currentTimeMillis() - startTime;
            log.info("class-method-timing: {} run {}ms", proceedingJoinPoint.getSignature().getName(), execTiming);
        }
        return null;
    }
}
