package id.tensky.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import id.tensky.core.data.local.entity.DiscoverMovieEntity
import id.tensky.core.data.local.source.IMovieListLocalSource
import id.tensky.core.data.mapper.MovieListMapper.toEntity
import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.source.IMovieListRemoteSource
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MovieDiscoverListRemoteMediator(
    private val apiToken: String,
    private val localSource: IMovieListLocalSource,
    private val remoteSource: IMovieListRemoteSource
) : RemoteMediator<Int, DiscoverMovieEntity>() {
    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DiscoverMovieEntity>
    ): MediatorResult {
        return try {
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    currentPage += 1
                    currentPage
                }
            } ?: 1
            val networkResult = remoteSource.getMovieDiscoverList(apiToken, loadKey)
            if (networkResult is ApiResponse.Success) {
                if (networkResult.data.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                localSource.insertDiscoverMovieList(networkResult.data.map {
                    it.toEntity()
                })
            }
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}