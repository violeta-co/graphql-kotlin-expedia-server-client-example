package generated

import com.expediagroup.graphql.client.Generated
import com.expediagroup.graphql.client.types.GraphQLClientRequest
import kotlin.String
import kotlin.reflect.KClass
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

public const val HELLO_WORLD_QUERY: String = "query HelloWorldQuery {\n    helloWorld\n}"

@Generated
@Serializable
public class HelloWorldQuery : GraphQLClientRequest<HelloWorldQuery.Result> {
  @Required
  public override val query: String = HELLO_WORLD_QUERY

  @Required
  public override val operationName: String = "HelloWorldQuery"

  public override fun responseType(): KClass<HelloWorldQuery.Result> = HelloWorldQuery.Result::class

  @Generated
  @Serializable
  public data class Result(
    public val helloWorld: String
  )
}
