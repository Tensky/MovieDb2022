package id.tensky.core.data.remote.source

import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.response.DiscoverMovieResponse
import id.tensky.core.data.remote.response.TrendingMovieResponse
import id.tensky.core.data.remote.service.MovieListEndpoint

interface IMovieListRemoteSource {
    suspend fun getMovieTrendingList(
        apiToken: String,
        page: Int
    ): ApiResponse<List<TrendingMovieResponse>>

    suspend fun getMovieDiscoverList(
        apiToken: String,
        page: Int
    ): ApiResponse<List<DiscoverMovieResponse>>
}

class MovieListRemoteSource(private val endpoint: MovieListEndpoint) : IMovieListRemoteSource {
    override suspend fun getMovieTrendingList(
        apiToken: String,
        page: Int
    ): ApiResponse<List<TrendingMovieResponse>> {
        return try {
            val response = endpoint.getTrendingMovieList(apiToken, page)
            if (response.isSuccessful && response.body() != null) {
                (ApiResponse.Success(response.body()!!.results))
            } else {
                (ApiResponse.Empty)
            }
        } catch (e: Exception) {
            (ApiResponse.Error(e.toString()))
        }
    }

    override suspend fun getMovieDiscoverList(
        apiToken: String,
        page: Int
    ): ApiResponse<List<DiscoverMovieResponse>> {
        return try {
            val response = endpoint.getDiscoverMovieList(apiToken, page)
            if (response.isSuccessful && response.body() != null) {
                (ApiResponse.Success(response.body()!!.results))
            } else {
                (ApiResponse.Empty)
            }
        } catch (e: Exception) {
            (ApiResponse.Error(e.toString()))
        }
    }
}