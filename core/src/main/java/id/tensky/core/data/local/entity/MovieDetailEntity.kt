package id.tensky.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "adult") var adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String? = null,
    @ColumnInfo(name = "budget") var budget: Int? = null,
    @ColumnInfo(name = "homepage") var homepage: String? = null,
    @ColumnInfo(name = "imdb_id") var imdbId: String? = null,
    @ColumnInfo(name = "original_language") var originalLanguage: String? = null,
    @ColumnInfo(name = "original_title") var originalTitle: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "popularity") var popularity: Double? = null,
    @ColumnInfo(name = "poster_path") var posterPath: String? = null,
    @ColumnInfo(name = "release_date") var releaseDate: String? = null,
    @ColumnInfo(name = "revenue") var revenue: Long? = null,
    @ColumnInfo(name = "runtime") var runtime: Int? = null,
    @ColumnInfo(name = "status") var status: String? = null,
    @ColumnInfo(name = "tagline") var tagline: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "video") var video: Boolean? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = null
)