package graphql.kotlin.expedia.server.client.example.graphql

import com.expediagroup.graphql.generator.execution.GraphQLContext
import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import io.ktor.server.request.ApplicationRequest

data class AuthorizedContext(
    val authorizedUser: String,
) : GraphQLContext

internal class ContextFactory : GraphQLContextFactory<AuthorizedContext, ApplicationRequest> {

    override suspend fun generateContext(request: ApplicationRequest): AuthorizedContext {
        return AuthorizedContext("")
    }
}
