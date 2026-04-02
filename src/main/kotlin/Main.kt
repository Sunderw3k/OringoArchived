package rip.sunrise

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.security.KeyStore

fun main() {
    val ksAlias = "server"
    val ksPassword = "changeit"

    val keyStoreFile = File("certs/server.jks")
    val keyStore = keyStoreFile.inputStream().use {
        KeyStore.getInstance("JKS").apply {
            load(it, ksPassword.toCharArray())
        }
    }

    println("Starting server on :8443")
    embeddedServer(Netty, configure = {
        sslConnector(
            keyStore = keyStore,
            keyAlias = ksAlias,
            keyStorePassword = { ksPassword.toCharArray() },
            privateKeyPassword = { ksPassword.toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
    }) {
        routing {
            get("/ext/rootCA.pem") {
                call.respondBytes(keyStore.getCertificate("root").encoded)
            }
        }
    }.start(wait = true)
}