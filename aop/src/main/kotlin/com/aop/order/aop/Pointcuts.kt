package com.aop.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {

    // 포인트컷 어노테이션으로 분리
    @Pointcut("execution(* com.aop.order..*(..))")
    fun allOrder() {}   // 포인트컷 시그니처

    // 서비스레이어에 적용
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {}

    @Pointcut("allOrder() && allService()")
    fun orderAndService() {}

}