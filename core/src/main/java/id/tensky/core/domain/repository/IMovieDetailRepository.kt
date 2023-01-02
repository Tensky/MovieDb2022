package id.tensky.core.domain.repository

import id.tensky.core.domain.model.MovieDetail
import id.tensky.core.util.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieDetailRepository {
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
}