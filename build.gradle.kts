import com.expediagroup.graphql.plugin.gradle.config.GraphQLSerializer

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.expediagroup.graphql") version "6.0.0-alpha.1"
    application
}

application {
    mainClassName = "graphql.kotlin.expedia.server.client.example.ApplicationKt"
}

repositories {
    mavenCentral()
}

dependencies {
    val graphql = "6.0.0-alpha.1"
    val kotlinx = "1.3.2"
    val ktor = "2.0.0"

    implementation("com.expediagroup:graphql-kotlin-server:$graphql")
    implementation("com.expediagroup:graphql-kotlin-ktor-client:$graphql")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinx")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx")

    implementation("io.ktor:ktor-server-core:$ktor")
    implementation("io.ktor:ktor-server-netty:$ktor")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor")
    implementation("io.ktor:ktor-serialization:$ktor")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")

    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
}

graphql {
    schema {
        packages = listOf("graphql.kotlin.expedia.server.client.example.generated")
    }
    client {
        endpoint = "http://localhost:8080/graphql"
        packageName = "graphql.kotlin.expedia.server.client.example.generated"

        allowDeprecatedFields = true
        serializer = GraphQLSerializer.KOTLINX
    }
}

val graphqlIntrospectSchema by tasks.getting(
    com.expediagroup.graphql.plugin.gradle.tasks.GraphQLIntrospectSchemaTask::class) {
    endpoint.set("http://localhost:8080/graphql")
}
