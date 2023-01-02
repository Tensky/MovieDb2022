package id.tensky.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.local.dao.DiscoverMovieDao
import id.tensky.core.data.local.dao.MovieDetailDao
import id.tensky.core.data.local.dao.TrendingMovieDao
import id.tensky.core.data.local.db.MovieDatabase
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideTrendingMovieListDao(
        @Named("movieDatabase") db: MovieDatabase
    ): TrendingMovieDao {
        return db.trendingMovieListDao()
    }

    @Provides
    fun provideDiscoverMovieListDao(
        @Named("movieDatabase") db: MovieDatabase
    ): DiscoverMovieDao {
        return db.discoverMovieListDao()
    }

    @Provides
    fun provideMovieDetailDao(
        @Named("movieDatabase") db: MovieDatabase
    ): MovieDetailDao {
        return db.movieDetailDao()
    }
}