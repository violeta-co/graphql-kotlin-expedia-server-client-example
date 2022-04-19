package graphql.kotlin.expedia.server.client.example.graphql

import com.expediagroup.graphql.server.operations.Query

class SimpleQuery : Query {
    fun helloWorld() = "Hello World!"
}
