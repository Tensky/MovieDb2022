package id.tensky.core.domain.repository

import androidx.paging.PagingData
import id.tensky.core.domain.model.Movie
import id.tensky.core.util.constants.ApiKeyConstant
import kotlinx.coroutines.flow.Flow

interface IMovieListRepository {
    fun getTrendingMovieListPaging(apiToken: String = ApiKeyConstant.API_KEY): Flow<PagingData<Movie>>
    fun getDiscoverMovieListPaging(apiToken: String = ApiKeyConstant.API_KEY): Flow<PagingData<Movie>>
}