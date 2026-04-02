package rip.sunrise.oringo

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.security.KeyStore

fun main() {
    val baseDir = File("files")
    check(baseDir.exists()) { "`files` directory does not exist" }

    val ksPassword = "changeit"

    val keyStoreFile = File("certs/server.jks")
    val keyStore = keyStoreFile.inputStream().use {
        KeyStore.getInstance("JKS").apply {
            load(it, ksPassword.toCharArray())
        }
    }

    println("Starting server on :443")
    embeddedServer(Netty, configure = {
        connector {
            port = 80
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "server",
            keyStorePassword = { ksPassword.toCharArray() },
            privateKeyPassword = { ksPassword.toCharArray() }) {
            port = 443
            keyStorePath = keyStoreFile
        }
    }) {
        routing {
            get("/") {
                println("GET /")
                call.respondText("")
            }

            get("/assets/") {
                println("GET /assets/")
                call.respondFile(baseDir.resolve("assets"))
            }

            post("/code/") {
                println("POST /code/")
                // The solved one is 0, the easiest one.
                call.respondText("465")
            }

            get("/auth2/") {
                println("GET /auth2/")
                call.respondFile(baseDir.resolve("auth2"))
            }

            post("/auth2/") {
                println("POST /auth2/")
                call.respondFile(baseDir.resolve("0_465"))
            }

            get("/ext/rootCA.pem") {
                call.respondBytes(keyStore.getCertificate("root").encoded)
            }
        }
    }.start(wait = true)
}