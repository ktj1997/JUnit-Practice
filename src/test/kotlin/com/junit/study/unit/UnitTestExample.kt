package com.junit.study.unit

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UnitTestExample {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            logger.info("Before All")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            logger.info("AfterAll")
        }
    }

    @BeforeEach
    fun beforeEach() {
        logger.info("BeforeEach")
    }
    @AfterEach
    fun afterEach() {
        logger.info("AfterEach")
    }

    @Test
    @Unit
    fun unitTest() {
        logger.info("unitTest")
    }
}
