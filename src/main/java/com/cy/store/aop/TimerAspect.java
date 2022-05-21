package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // 将当前类的对象的创建和维护交由spring容器维护
@Aspect // 将当前类标记为切面类
public class TimerAspect {
    // 环绕通知方法
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = pjp.proceed(); // 调用目标方法
        long end = System.currentTimeMillis();
        System.out.println("time spend:" + (end - start));
        return res;

    }
}
