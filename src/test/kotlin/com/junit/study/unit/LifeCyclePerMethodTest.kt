package com.junit.study.unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Junit의 인스턴스 기본전략은 PER_METHOD
 * Mehotd를 실행 할 때 마다 다른 인스턴스가 사용된다.
 * 그렇기에 테스트 순서에서 자유롭다.
 * 테스트간의 의존성을 없애기 위함이다.
 */
class LifeCyclePerMethodTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }
    var counter: Int = 1

    /**
     * 테스트 메소드마다 다른 인스턴스를 사용하고 있기 때문에
     * counter의 값이 공유되지 않는다.
     */

    @UnitTest
    @Test
    fun firstCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 1)
    }

    @UnitTest
    @Test
    fun secondCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 1)
    }

    @UnitTest
    @Test
    fun thirdCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 1)
    }
}
