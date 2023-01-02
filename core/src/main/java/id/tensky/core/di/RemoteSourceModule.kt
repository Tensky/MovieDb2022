package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.remote.service.AuthenticationEndpoint
import id.tensky.core.data.remote.service.MovieDetailEndpoint
import id.tensky.core.data.remote.service.MovieListEndpoint
import id.tensky.core.data.remote.source.*

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Provides
    fun provideAuthenticationRemoteSource(
        endpoint: AuthenticationEndpoint
    ): IAuthenticationRemoteSource {
        return AuthenticationRemoteSource(endpoint)
    }

    @Provides
    fun provideMovieListRemoteSource(
        endpoint: MovieListEndpoint
    ): IMovieListRemoteSource {
        return MovieListRemoteSource(endpoint)
    }

    @Provides
    fun provideMovieDetailRemoteSource(
        endpoint: MovieDetailEndpoint
    ): IMovieDetailRemoteSource {
        return MovieDetailRemoteSource(endpoint)
    }
}