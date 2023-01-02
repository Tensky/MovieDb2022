package id.tensky.core.data.local.source

import id.tensky.core.data.local.dao.MovieDetailDao
import id.tensky.core.data.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

interface IMovieDetailLocalSource {
    fun getMovieDetail(itemId: Int): Flow<MovieDetailEntity>

    suspend fun insertMovieDetail(item: MovieDetailEntity)
}

class MovieDetailLocalSource(private val dao: MovieDetailDao) : IMovieDetailLocalSource {
    override fun getMovieDetail(itemId: Int): Flow<MovieDetailEntity> =
        dao.getMovieDetailItem(itemId).filterNotNull()

    override suspend fun insertMovieDetail(item: MovieDetailEntity) = dao.insertMovieDetail(item)
}