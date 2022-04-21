package graphql.kotlin.expedia.server.client.example

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import graphql.kotlin.expedia.server.client.example.graphql.KtorGraphQlController
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import mu.KotlinLogging

val log = KotlinLogging.logger("graphql.kotlin.expedia.server.client.example").apply {
    val logback = underlyingLogger as Logger
    logback.level = Level.toLevel("DEBUG")
}

object Server {
    private const val port = 8080

    private val server = embeddedServer(factory = Netty, port = port) {

        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
        }

        routing {
            val controller = KtorGraphQlController()
            this.post("/graphql") {
                controller.handle(this)
            }
        }
    }

    fun start() {
        server.start()
        log.debug { "Server responding at http:/localhost:$port" }
    }

    fun stop() = server.stop(100, 100)
}
