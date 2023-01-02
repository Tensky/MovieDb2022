package id.tensky.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.tensky.core.data.local.entity.TrendingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMovieDao {
    @Query("SELECT * FROM trending_movie")
    fun getMovieList(): Flow<TrendingMovieEntity>

    @Query("SELECT * FROM trending_movie")
    fun getMovieListPagingSource(): PagingSource<Int, TrendingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(itemList: List<TrendingMovieEntity>)
}