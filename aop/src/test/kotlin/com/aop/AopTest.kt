package com.aop

import com.aop.order.OrderRepository
import com.aop.order.OrderService
import com.aop.order.aop.AspectV1
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(AspectV1::class)    // 빈으로 등록
class AopTest @Autowired constructor(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,
) {

    private val log = KotlinLogging.logger {}

    @Test
    fun appInfo() {
        log.info { "isAopProxy, orderService=${AopUtils.isAopProxy(orderService)}"}
        log.info { "isAopProxy, orderRepository=${AopUtils.isAopProxy(orderRepository)}"}

    }

    @Test
    fun success() {
        orderService.orderItem("itemA")

    }

    @Test
    fun exception() {
        assertThatThrownBy { orderService.orderItem("ex") }.isInstanceOf(IllegalStateException::class.java)

    }
}