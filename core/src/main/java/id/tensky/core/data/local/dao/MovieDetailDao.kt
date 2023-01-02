package id.tensky.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.tensky.core.data.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(item: MovieDetailEntity)

    @Query("SELECT * FROM movie_detail WHERE id = :itemId LIMIT 1")
    fun getMovieDetailItem(itemId: Int): Flow<MovieDetailEntity>
}