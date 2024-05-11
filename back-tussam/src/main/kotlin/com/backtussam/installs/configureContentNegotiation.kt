package com.backtussam.installs

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}