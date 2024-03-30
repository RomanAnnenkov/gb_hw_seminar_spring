package ru.gb.hwSeminarTwo.aspects;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log
public class LogUserAction {

    @Before(value = "@annotation(TrackUserAction)")
    public void logAction(JoinPoint joinPoint) throws Throwable {
        String toLog = String.format("%s execute method '%s' with parameters '%s'",
                joinPoint.getThis().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                Arrays.stream(joinPoint.getArgs()).toList());
        log.info(toLog);
    }
}
