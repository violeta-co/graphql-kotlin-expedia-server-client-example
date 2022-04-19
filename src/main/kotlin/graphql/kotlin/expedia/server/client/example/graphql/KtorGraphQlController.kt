package graphql.kotlin.expedia.server.client.example.graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.expediagroup.graphql.server.execution.GraphQLServer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.ApplicationRequest
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext

class KtorGraphQlController {
    private val schema = buildGraphQlSchema()
    private val gql = GraphQL.newGraphQL(schema).build()
    private val mapper = jacksonObjectMapper()
    private val server = buildGraphQlServer(mapper, gql)

    suspend fun handle(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        val call = pipelineContext.call
        val result = server.execute(call.request)

        if (result == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            runCatching {
                val json = mapper.writeValueAsString(result)
                call.respond(json)
            }.onFailure {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}

private fun buildGraphQlSchema(): GraphQLSchema {
    val config = SchemaGeneratorConfig(
        supportedPackages = listOf("graphql.kotlin.expedia.server.client.example"),
    )

    val queries = listOf<TopLevelObject>(
        TopLevelObject(SimpleQuery()),
    )

    val mutations = listOf<TopLevelObject>(
        TopLevelObject(SimpleQuery()),
    )

    return toSchema(config, queries, mutations)
}

private fun buildGraphQlServer(
    mapper: ObjectMapper,
    gql: GraphQL,
): GraphQLServer<ApplicationRequest> {
    val dataLoader = DataLoaderFactory()
    val context = ContextFactory()
    val parser = RequestParser(mapper)
    val handler = GraphQLRequestHandler(gql, dataLoader)

    return GraphQLServer(parser, context, handler)
}
