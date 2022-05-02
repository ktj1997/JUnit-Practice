package com.junit.study.unit

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RepeatedTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * n번만큼 반복하는 테스트
     */
    @UnitTest
    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{currentRepetition}/{totalRepetitions}")
    fun repeatedTest(repetitionInfo: RepetitionInfo) {
        logger.info("Test is started ${repetitionInfo.currentRepetition}/${repetitionInfo.totalRepetitions}")
    }
}
