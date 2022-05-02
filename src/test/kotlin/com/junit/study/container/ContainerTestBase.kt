package com.junit.study.container

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.time.Duration

@SpringBootTest
@Testcontainers
class ContainerTestBase {

    companion object {
        @Container
        @JvmStatic
        private val instance: KDockerComposeContainer =
            KDockerComposeContainer(ClassPathResource("./docker-compose.yml").file)
                .withExposedService(MySqlProperties.SERVICE_NAME, MySqlProperties.PORT, Wait.defaultWaitStrategy().withStartupTimeout(Duration.ofMinutes(5)))
                .withExposedService(MyNodeProperties.SERVICE_NAME, MyNodeProperties.PORT, Wait.defaultWaitStrategy().withStartupTimeout(Duration.ofMinutes(5)))

        @JvmStatic
        @DynamicPropertySource
        fun registerMySqlProperty(registry: DynamicPropertyRegistry) {

            val host = instance.getServiceHost(MySqlProperties.SERVICE_NAME, MySqlProperties.PORT)
            val port = instance.getServicePort(MySqlProperties.SERVICE_NAME, MySqlProperties.PORT)
            registry.add("spring.datasource.url") { "jdbc:mysql://$host:$port/sample" }
            registry.add("spring.datasource.password") { "1q2w3e4r" }
            registry.add("spring.datasource.username") { "root" }
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerMyNodeProperty(registry: DynamicPropertyRegistry) {
            val host = instance.getServiceHost(MyNodeProperties.SERVICE_NAME, MyNodeProperties.PORT)
            val port = instance.getServicePort(MyNodeProperties.SERVICE_NAME, MyNodeProperties.PORT)
            registry.add("my-node.url") { "http://$host:$port" }
        }
    }
}
class KDockerComposeContainer(composeFile: File) : DockerComposeContainer<KDockerComposeContainer>(composeFile)
