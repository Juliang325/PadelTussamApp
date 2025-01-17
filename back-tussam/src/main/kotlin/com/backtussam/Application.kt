package com.backtussam

import com.backtussam.db.DatabaseFactory
import com.backtussam.installs.configureCORS
import com.backtussam.installs.configureContentNegotiation
import com.backtussam.repositories.PlayerRepository
import com.backtussam.repositories.PlayerRepositoryImpl
import com.backtussam.routes.authRoutes
import com.backtussam.security.configureSecurity
import com.backtussam.services.PlayerService
import com.backtussam.services.PlayerServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.jackson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1"){
        myApplicationModule()

    }.start(wait = true)
}

fun Application.myApplicationModule() {
    DatabaseFactory.init()
    configureContentNegotiation()
    configureCORS()
    configureSecurity()

    val service: PlayerService = PlayerServiceImpl()
    val repository: PlayerRepository = PlayerRepositoryImpl(service)

    authRoutes(repository)
    routing {
        authenticate {
            get("/testUrl") {
                call.respondText("Hello World")
            }
        }
    }
}
