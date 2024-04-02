package com.example.hwSeminarEightPayment.aspects;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Aspect
@Controller
@Log
public class LoggingAspect {
    @Before(value = "@annotation(TrackUserAction)")
    public void logAction(JoinPoint joinPoint) throws Throwable {
        String toLog = String.format("%s execute method '%s' with parameters '%s'",
                joinPoint.getThis().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                Arrays.stream(joinPoint.getArgs()).toList());
        log.info(toLog);
    }
}
