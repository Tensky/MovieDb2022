package id.tensky.core.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.tensky.core.data.local.dao.DiscoverMovieDao
import id.tensky.core.data.local.dao.MovieDetailDao
import id.tensky.core.data.local.dao.TrendingMovieDao
import id.tensky.core.data.local.entity.DiscoverMovieEntity
import id.tensky.core.data.local.entity.MovieDetailEntity
import id.tensky.core.data.local.entity.TrendingMovieEntity
import id.tensky.core.util.converter.RoomTypeConverter

@Database(
    entities = [TrendingMovieEntity::class, DiscoverMovieEntity::class, MovieDetailEntity::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(RoomTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun trendingMovieListDao(): TrendingMovieDao
    abstract fun discoverMovieListDao(): DiscoverMovieDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie.db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}