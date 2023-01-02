package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.remote.service.AuthenticationEndpoint
import id.tensky.core.data.remote.service.MovieDetailEndpoint
import id.tensky.core.data.remote.service.MovieListEndpoint
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object EndpointModule {
    @Provides
    fun provideAuthenticationEndpoint(
        @Named("baseApi") baseApi: Retrofit
    ): AuthenticationEndpoint {
        return baseApi.create(AuthenticationEndpoint::class.java)
    }

    @Provides
    fun provideMovieListEndpoint(
        @Named("baseApi") baseApi: Retrofit
    ): MovieListEndpoint {
        return baseApi.create(MovieListEndpoint::class.java)
    }

    @Provides
    fun provideMovieDetailEndpoint(
        @Named("baseApi") baseApi: Retrofit
    ): MovieDetailEndpoint {
        return baseApi.create(MovieDetailEndpoint::class.java)
    }
}