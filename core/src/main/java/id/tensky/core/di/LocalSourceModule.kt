package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.local.dao.DiscoverMovieDao
import id.tensky.core.data.local.dao.MovieDetailDao
import id.tensky.core.data.local.dao.TrendingMovieDao
import id.tensky.core.data.local.source.IMovieDetailLocalSource
import id.tensky.core.data.local.source.IMovieListLocalSource
import id.tensky.core.data.local.source.MovieDetailLocalSource
import id.tensky.core.data.local.source.MovieListLocalSource

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {
    @Provides
    fun provideMovieListLocalSource(
        trendingMovieDao: TrendingMovieDao,
        discoverMovieDao: DiscoverMovieDao
    ): IMovieListLocalSource {
        return MovieListLocalSource(trendingMovieDao, discoverMovieDao)
    }

    @Provides
    fun provideMovieDetailLocalSource(
        dao: MovieDetailDao,
    ): IMovieDetailLocalSource {
        return MovieDetailLocalSource(dao)
    }
}