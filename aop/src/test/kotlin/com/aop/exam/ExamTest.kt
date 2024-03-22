package com.aop.exam

import com.aop.exam.aop.RetryAspect
import com.aop.exam.aop.TraceAspect
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
//@Import(TraceAspect::class)
@Import(TraceAspect::class, RetryAspect::class)
class ExamTest @Autowired constructor(
    private val examService: ExamService,
) {

    private val log = KotlinLogging.logger {}


    @Test
    fun test() {
        for (i in 0..4) {
            log.info { "client request i=$i" }
            examService.request("data$i")
        }
    }
}