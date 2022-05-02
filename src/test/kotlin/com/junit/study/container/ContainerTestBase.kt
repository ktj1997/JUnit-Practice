package com.junit.study.container

import org.springframework.core.io.ClassPathResource
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File

@Testcontainers
class ContainerTestBase {

    companion object{
        @JvmStatic
        @Container
        val instance =
            KDockerComposeContainer(ClassPathResource("./docker-compose.yml").file)
                .withExposedService("db-mysql",3306)
                .withExposedService("my-node",3000)
    }

}
class KDockerComposeContainer(composeFile : File) : DockerComposeContainer<KDockerComposeContainer>(composeFile)