package com.junit.study.container

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get

class ContainerTestSample @Autowired constructor(
    private val env: Environment
) : ContainerTestBase() {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this.javaClass)
    }

    @Test
    @ContainerTest
    @DisplayName("컨테이너 테스트 샘플")
    fun startContainerTest() {
        val myNodeURL = env["my-node.url"]
        log.info(myNodeURL)

        throw RuntimeException()
    }
}
