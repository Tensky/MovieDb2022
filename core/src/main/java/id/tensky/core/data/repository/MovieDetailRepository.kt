package id.tensky.core.data.repository

import android.util.Log
import id.tensky.core.data.local.source.IMovieDetailLocalSource
import id.tensky.core.data.mapper.MovieDetailMapper
import id.tensky.core.data.mapper.MovieDetailMapper.toDomainModel
import id.tensky.core.data.mapper.MovieDetailMapper.toEntity
import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.response.MovieDetailResponse
import id.tensky.core.data.remote.source.IMovieDetailRemoteSource
import id.tensky.core.domain.model.MovieDetail
import id.tensky.core.domain.repository.IMovieDetailRepository
import id.tensky.core.util.network_bound_resource.NetworkBoundResource
import id.tensky.core.util.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailRepository(
    private val apiKey: String,
    private val localSource: IMovieDetailLocalSource,
    private val remoteSource: IMovieDetailRemoteSource
) : IMovieDetailRepository {
    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                val result = localSource.getMovieDetail(movieId)
                return result.map {
                    toDomainModel(it)
                }
            }

            override fun shouldFetch(data: MovieDetail?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteSource.getMovieDetail(apiKey, movieId)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val entity = data.toEntity()
                localSource.insertMovieDetail(entity)
            }
        }.asFlow()
}