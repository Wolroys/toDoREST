package com.wolroys.todorest.ascpect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("within(com.wolroys.todorest.controller.*Controller)")
    void isControllerLayer(){
    }

    @Pointcut("within(com.wolroys.todorest.service.*Service)")
    void isServiceLayer(){}

    @After("(isControllerLayer() || isServiceLayer()) && target(clazz)")
    public void addLoggingForControllers(JoinPoint joinPoint, Object clazz){
        log.info("invoked class: {} with method: {}", clazz.getClass().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "isControllerLayer() && target(controller)", throwing = "e")
    public void addLoggingForExceptions(Throwable e, Object controller){
        log.warn("An error {}: {} was received in the controller: {}", e.getClass().getSimpleName(), e.getMessage(),
                controller.getClass().getSimpleName());
    }

}
