package id.tensky.core.domain.usecase

import id.tensky.core.domain.model.MovieDetail
import id.tensky.core.domain.repository.IMovieDetailRepository
import id.tensky.core.util.resource.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailUseCase {
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
}

class MovieDetailInteractor(private val repository: IMovieDetailRepository) : MovieDetailUseCase {
    override fun getMovieDetail(movieId: Int) = repository.getMovieDetail(movieId)
}
