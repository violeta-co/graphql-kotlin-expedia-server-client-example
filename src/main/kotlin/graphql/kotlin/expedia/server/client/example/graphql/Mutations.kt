package graphql.kotlin.expedia.server.client.example.graphql

import com.expediagroup.graphql.server.operations.Mutation

class SimpleMutation : Mutation {
    fun hello(value: Int): String {
        val result = ""

        repeat(value) {
            result + "hello"
        }

        return result
    }
}
