package com.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectV3 {

    private val log = KotlinLogging.logger {}

    // 포인트컷 어노테이션으로 분리
    @Pointcut("execution(* com.aop.order..*(..))")
    fun allOrder() {}   // 포인트컷 시그니처

    // 서비스레이어에 적용
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {}

    @Around("allOrder()")                                // 포인트컷
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {    // 어드바이스
        log.info { "[log] ${joinPoint.signature}" }
        return joinPoint.proceed()
    }

    // com.aop.order 패키지 & 하위 패키지이면서 클래스 이름 패턴 *Service
    @Around("allOrder() && allService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
        try {
            log.info { "[트랜잭션 시작] ${joinPoint.signature}" }
            val result = joinPoint.proceed()
            log.info { "[트랜잭션 커밋] ${joinPoint.signature}" }
            return result
        } catch (e: Exception) {
            log.info { "[트랜잭션 롤백] ${joinPoint.signature}" }
            throw e
        } finally {
            log.info { "[리소스 릴리즈] ${joinPoint.signature}" }
        }
    }

}