package com.junit.study.container

import org.junit.jupiter.api.Tag

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tag("container")
annotation class ContainerTest()
