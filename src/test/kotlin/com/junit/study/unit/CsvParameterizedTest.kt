package com.junit.study.unit

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.provider.CsvSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CsvParameterizedTest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)

        /**
         * 여러가지의 Argument는 Aggregator를 통해서 접근 가능하다.
         * CustomAggregator는 Public Class이거나 Static Class여야한다.
         */
        class PeopleAggregator : ArgumentsAggregator {
            override fun aggregateArguments(accessor: ArgumentsAccessor?, context: ParameterContext?): Any {
                return People(
                    age = accessor!!.getInteger(0),
                    name = accessor.getString(1)
                )
            }
        }
    }
    /**
     * CSV Source 기본 delimiter : ','
     * 문자열을 인자로 사용할 때는 공백을 ' '을 통해서 표현 가능하다.
     */
    @Unit
    @ParameterizedTest
    @DisplayName("기본 제공 Aggregator")
    @CsvSource(value = ["26, 'kim taejun'", "24, lee"])
    fun csvParameterizedTestWithArgumentAggregator(argumentsAccessor: ArgumentsAccessor) {
        val people = People(
            age = argumentsAccessor.getInteger(0),
            name = argumentsAccessor.getString(1)
        )

        logger.info(people.toString())
    }

    @Unit
    @ParameterizedTest
    @DisplayName("Custom Aggregator")
    @CsvSource(value = ["26, 'kim taejun'", "24, lee"])
    fun csvParameterizedTestWithCustomArgumentAggregator(@AggregateWith(PeopleAggregator::class) people: People) {
        logger.info(people.toString())
    }
}
data class People(val name: String, val age: Int)
