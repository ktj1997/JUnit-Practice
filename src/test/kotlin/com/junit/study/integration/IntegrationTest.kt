package com.junit.study.integration

import org.junit.jupiter.api.Tag

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tag("integration")
annotation class IntegrationTest()
