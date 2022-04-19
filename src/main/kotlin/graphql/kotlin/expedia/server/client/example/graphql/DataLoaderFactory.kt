package graphql.kotlin.expedia.server.client.example.graphql

import com.expediagroup.graphql.dataloader.DataLoaderRegistryFactory
import org.dataloader.DataLoaderRegistry

internal class DataLoaderFactory : DataLoaderRegistryFactory {
    override fun generate(): DataLoaderRegistry {
        val registry = DataLoaderRegistry()
        return registry
    }
}
