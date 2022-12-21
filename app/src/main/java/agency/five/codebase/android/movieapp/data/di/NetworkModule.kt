package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.network.MovieServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module
import java.net.InetSocketAddress
import java.net.Proxy


val networkModule = module {
    single<MovieService> { MovieServiceImpl(client = get()) }
    single {
        HttpClient(Android) {
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
                proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
            }
        }
    }
}