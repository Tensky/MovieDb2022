package id.tensky.core.data.repository

import androidx.paging.*
import id.tensky.core.data.local.source.IMovieListLocalSource
import id.tensky.core.data.mapper.MovieListMapper.toDomainModel
import id.tensky.core.data.paging.MovieDiscoverListRemoteMediator
import id.tensky.core.data.paging.MovieTrendingListRemoteMediator
import id.tensky.core.data.remote.source.IMovieListRemoteSource
import id.tensky.core.domain.model.Movie
import id.tensky.core.domain.repository.IMovieListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieListRepository(
    private val localSource: IMovieListLocalSource,
    private val remoteSource: IMovieListRemoteSource
) : IMovieListRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrendingMovieListPaging(apiToken: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 500,
                enablePlaceholders = false,

                ),
            remoteMediator = MovieTrendingListRemoteMediator(apiToken, localSource, remoteSource)
        ) {
            localSource.getTrendingMovieListPaging()
        }.flow.map { pagingData -> pagingData.map { entity -> entity.toDomainModel() } }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getDiscoverMovieListPaging(apiToken: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 500,
                enablePlaceholders = false,

                ),
            remoteMediator = MovieDiscoverListRemoteMediator(apiToken, localSource, remoteSource)
        ) {
            localSource.getDiscoverMovieListPaging()
        }.flow.map { pagingData -> pagingData.map { entity -> entity.toDomainModel() } }
    }
}