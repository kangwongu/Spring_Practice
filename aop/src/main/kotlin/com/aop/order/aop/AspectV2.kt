package com.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectV2 {

    private val log = KotlinLogging.logger {}

    // 포인트컷 어노테이션으로 분리
    @Pointcut("execution(* com.aop.order..*(..))")
    fun allOrder() {}   // 포인트컷 시그니처

    @Around("allOrder()")                                // 포인트컷
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {    // 어드바이스
        log.info { "[log] ${joinPoint.signature}" }
        return joinPoint.proceed()
    }

}