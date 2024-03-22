package com.aop.exam.aop

import com.aop.exam.annotation.Retry
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class RetryAspect {

    private val log = KotlinLogging.logger {}

    // Retry 어노테이션을 붙이면 적용
    @Around("@annotation(retry)")
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any? {
        log.info { "[retry] ${joinPoint.signature} retry=${retry}" }

        val maxRetry = retry.value
        var exceptionHolder: Exception? = null

        for (retryCount in 1..maxRetry) {
            try {
                log.info { "[retry] try count=${retryCount}/${maxRetry}" }
                return joinPoint.proceed()
            } catch (e: Exception) {
                exceptionHolder = e
            }
        }
        throw exceptionHolder!!
    }
}