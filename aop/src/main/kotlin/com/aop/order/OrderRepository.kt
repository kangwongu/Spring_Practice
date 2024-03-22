package com.aop.order

import mu.KotlinLogging
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    private val log = KotlinLogging.logger {}

    fun save(itemId: String): String {
        log.info { "[orderRepository] 실행" }

        if (itemId == "ex") {
            throw IllegalStateException("예외")
        }

        return "ok"
    }
}