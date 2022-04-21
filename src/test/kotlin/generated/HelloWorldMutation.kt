package generated

import com.expediagroup.graphql.client.Generated
import com.expediagroup.graphql.client.types.GraphQLClientRequest
import kotlin.String
import kotlin.reflect.KClass
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

public const val HELLO_WORLD_MUTATION: String = "mutation HelloWorldMutation {\n    helloWorld\n}"

@Generated
@Serializable
public class HelloWorldMutation : GraphQLClientRequest<HelloWorldMutation.Result> {
  @Required
  public override val query: String = HELLO_WORLD_MUTATION

  @Required
  public override val operationName: String = "HelloWorldMutation"

  public override fun responseType(): KClass<HelloWorldMutation.Result> =
      HelloWorldMutation.Result::class

  @Generated
  @Serializable
  public data class Result(
    public val helloWorld: String
  )
}
