package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.domain.repository.IAuthenticationRepository
import id.tensky.core.domain.repository.IMovieDetailRepository
import id.tensky.core.domain.repository.IMovieListRepository
import id.tensky.core.domain.usecase.*


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAuthenticationUseCase(
        repository: IAuthenticationRepository
    ): AuthenticationUseCase {
        return AuthenticationInteractor(repository)
    }

    @Provides
    fun provideMovieListUseCase(
        repository: IMovieListRepository
    ): MovieListUseCase {
        return MovieListInteractor(repository)
    }

    @Provides
    fun provideMovieDetailUseCase(
        repository: IMovieDetailRepository
    ): MovieDetailUseCase {
        return MovieDetailInteractor(repository)
    }
}