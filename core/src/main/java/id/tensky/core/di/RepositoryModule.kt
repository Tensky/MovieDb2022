package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.local.source.IMovieDetailLocalSource
import id.tensky.core.data.local.source.IMovieListLocalSource
import id.tensky.core.data.remote.source.IAuthenticationRemoteSource
import id.tensky.core.data.remote.source.IMovieDetailRemoteSource
import id.tensky.core.data.remote.source.IMovieListRemoteSource
import id.tensky.core.data.repository.AuthenticationRepository
import id.tensky.core.data.repository.MovieDetailRepository
import id.tensky.core.data.repository.MovieListRepository
import id.tensky.core.domain.repository.IAuthenticationRepository
import id.tensky.core.domain.repository.IMovieDetailRepository
import id.tensky.core.domain.repository.IMovieListRepository
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAuthenticationRepository(
        @Named("apiKey") apiKey: String,
        remoteSource: IAuthenticationRemoteSource,
    ): IAuthenticationRepository {
        return AuthenticationRepository(apiKey, remoteSource)
    }

    @Provides
    fun provideMovieListRepository(
        localSource: IMovieListLocalSource,
        remoteSource: IMovieListRemoteSource
    ): IMovieListRepository {
        return MovieListRepository(
            localSource,
            remoteSource
        )
    }

    @Provides
    fun provideMovieDetailRepository(
        @Named("apiKey") apiKey: String,
        localSource: IMovieDetailLocalSource,
        remoteSource: IMovieDetailRemoteSource
    ): IMovieDetailRepository {
        return MovieDetailRepository(
            apiKey,
            localSource,
            remoteSource
        )
    }
}