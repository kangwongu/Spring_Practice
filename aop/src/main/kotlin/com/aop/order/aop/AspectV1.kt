package com.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class AspectV1 {

    private val log = KotlinLogging.logger {}

    @Around("execution(* com.aop.order..*(..))")         // 포인트컷
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {    // 어드바이스
        log.info { "[log] ${joinPoint.signature}" }
        return joinPoint.proceed()
    }

}