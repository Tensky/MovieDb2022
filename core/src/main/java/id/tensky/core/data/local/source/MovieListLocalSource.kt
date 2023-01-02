package id.tensky.core.data.local.source

import androidx.paging.PagingSource
import id.tensky.core.data.local.dao.DiscoverMovieDao
import id.tensky.core.data.local.dao.TrendingMovieDao
import id.tensky.core.data.local.entity.DiscoverMovieEntity
import id.tensky.core.data.local.entity.TrendingMovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieListLocalSource {
    fun getTrendingMovieList(): Flow<TrendingMovieEntity>
    fun getTrendingMovieListPaging(): PagingSource<Int, TrendingMovieEntity>
    suspend fun insertTrendingMovieList(itemList: List<TrendingMovieEntity>)
    fun getDiscoverMovieList(): Flow<DiscoverMovieEntity>
    fun getDiscoverMovieListPaging(): PagingSource<Int, DiscoverMovieEntity>
    suspend fun insertDiscoverMovieList(itemList: List<DiscoverMovieEntity>)
}

class MovieListLocalSource(
    private val trendingMovieDao: TrendingMovieDao,
    private val discoverMovieDao: DiscoverMovieDao
) : IMovieListLocalSource {
    override fun getTrendingMovieList(): Flow<TrendingMovieEntity> = trendingMovieDao.getMovieList()

    override fun getTrendingMovieListPaging(): PagingSource<Int, TrendingMovieEntity> =
        trendingMovieDao.getMovieListPagingSource()

    override suspend fun insertTrendingMovieList(itemList: List<TrendingMovieEntity>) =
        trendingMovieDao.insertMovieList(itemList)

    override fun getDiscoverMovieList(): Flow<DiscoverMovieEntity> = discoverMovieDao.getMovieList()

    override fun getDiscoverMovieListPaging(): PagingSource<Int, DiscoverMovieEntity> =
        discoverMovieDao.getMovieListPagingSource()

    override suspend fun insertDiscoverMovieList(itemList: List<DiscoverMovieEntity>) =
        discoverMovieDao.insertMovieList(itemList)
}