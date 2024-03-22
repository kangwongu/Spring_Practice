package com.aop.exam

import com.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {

    companion object {
        var seq = 0
    }

    @Trace
    fun save(itemId: String): String {
        seq++
        if (seq % 5 == 0) {
            throw IllegalStateException("예외")
        }
        return "OK"
    }
}