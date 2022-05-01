package com.junit.study.unit

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.converter.SimpleArgumentConverter
import org.junit.jupiter.params.provider.ValueSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ValueParameterizedTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    /**
     * @ValueSource에서 기본적으로 하나의 Argument를 가질 경우,
     * SimpleArgunemtConverter를 통해서 원하는 형태의 객체로 변환 시킬 수 있다.
     */

    @Unit
    @ParameterizedTest
    @DisplayName("String 파라미터 테스트")
    @ValueSource(strings = ["hello", "my", "name", "is", "taejun"])
    fun stringParameterizedTest(message: String) {
        logger.info("Current message is $message")
    }

    @Unit
    @ParameterizedTest
    @DisplayName("Integer 파라미터 테스트")
    @ValueSource(ints = ([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
    fun integerParameterizedTest(@ConvertWith(NumberConverter::class) number: Number) {
        logger.info("is ${number.value} Even? = ${number.value % 2 == 0}")
    }
}
class Number(val value: Int)

/**
 * Argunemt Converter는 하나의 Arugnemt에만 사용 가능하다.
 * Public Class이거나, Static Class여야 한다.
 */
class NumberConverter : SimpleArgumentConverter() {
    override fun convert(source: Any?, targetType: Class<*>?): Any {
        return source?.let { Number(it as Int) }
            ?: IllegalArgumentException("Non null type expected")
    }
}
