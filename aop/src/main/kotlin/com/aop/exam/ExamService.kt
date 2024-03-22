package com.aop.exam

import org.springframework.stereotype.Service

@Service
class ExamService(
    private val examRepository: ExamRepository,
) {

    fun request(itemId: String) {
        examRepository.save(itemId)
    }
}