package com.junit.study.unit

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * LifeCycle.PER_CLASS 일 때는
 * 테스트간의 인스턴스를 공유하며 하나의 인스턴스만을 사용한다.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class LifeCyclePerClassTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    var counter: Int = 1

    /**
     * LifeCycle.PER_CLASS 일 때는
     * @BeforeAll @AfterAll 이 Static일 필요가 없어진다.
     */
    @BeforeAll
    fun beforeAll() {
        logger.info("Before All")
    }

    @AfterAll
    fun afterAll() {
        logger.info("After All")
    }

    @Test
    @UnitTest
    @Order(1)
    fun firstCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 1)
    }

    @Test
    @UnitTest
    @Order(2)
    fun secondCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 2)
    }

    @Test
    @UnitTest
    @Order(3)
    fun thirdCounterTest() {
        logger.info(this.toString())
        assertEquals(counter++, 3)
    }
}
