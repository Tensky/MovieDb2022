package id.tensky.core.domain.usecase

import androidx.paging.PagingData
import id.tensky.core.domain.model.Movie
import id.tensky.core.domain.repository.IMovieListRepository
import kotlinx.coroutines.flow.Flow

interface MovieListUseCase {
    fun getTrendingMovieListPaging(): Flow<PagingData<Movie>>
    fun getDiscoverMovieListPaging(): Flow<PagingData<Movie>>
}

class MovieListInteractor(val repository: IMovieListRepository) : MovieListUseCase {
    override fun getTrendingMovieListPaging(): Flow<PagingData<Movie>> {
        return repository.getTrendingMovieListPaging()
    }

    override fun getDiscoverMovieListPaging(): Flow<PagingData<Movie>> {
        return repository.getDiscoverMovieListPaging()
    }
}