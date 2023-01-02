package id.tensky.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.tensky.core.data.local.entity.DiscoverMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscoverMovieDao {

    @Query("SELECT * FROM discover_movie")
    fun getMovieList(): Flow<DiscoverMovieEntity>

    @Query("SELECT * FROM discover_movie")
    fun getMovieListPagingSource(): PagingSource<Int, DiscoverMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(itemList: List<DiscoverMovieEntity>)
}