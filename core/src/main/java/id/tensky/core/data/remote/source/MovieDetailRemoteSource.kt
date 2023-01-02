package id.tensky.core.data.remote.source

import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.response.MovieDetailResponse
import id.tensky.core.data.remote.service.MovieDetailEndpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IMovieDetailRemoteSource {
    fun getMovieDetail(apiKey: String, movieId: Int): Flow<ApiResponse<MovieDetailResponse>>
}

class MovieDetailRemoteSource(private val endpoint: MovieDetailEndpoint) :
    IMovieDetailRemoteSource {
    override fun getMovieDetail(
        apiKey: String,
        movieId: Int
    ): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = endpoint.getMovieDetail(movieId.toString(), apiKey)
                if (response.isSuccessful && response.body() != null && response.body()!!.id != null) {
                    emit(ApiResponse.Success(response.body()!!))
                } else if (response.code() == 404) {
                    emit(ApiResponse.Error("Not Found"))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}