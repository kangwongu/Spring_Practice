package com.aop.order.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order

class AspectV5 {

    @Aspect
    @Order(2)
    class LogAspect {
        private val log = KotlinLogging.logger {}

        @Around("com.aop.order.aop.Pointcuts.allOrder()")    // 포인트컷
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {    // 어드바이스
            log.info { "[log] ${joinPoint.signature}" }
            return joinPoint.proceed()
        }

    }

    @Aspect
    @Order(1)
    class TxAspect {
        private val log = KotlinLogging.logger {}

        // com.aop.order 패키지 & 하위 패키지이면서 클래스 이름 패턴 *Service
        @Around("com.aop.order.aop.Pointcuts.orderAndService()")
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

}