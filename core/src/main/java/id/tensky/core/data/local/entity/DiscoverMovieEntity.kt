package id.tensky.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discover_movie")
data class DiscoverMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "adult") var adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String? = null,
    @ColumnInfo(name = "genre_ids") var genreIds: ArrayList<Int>? = null,
    @ColumnInfo(name = "original_language") var originalLanguage: String? = null,
    @ColumnInfo(name = "original_title") var originalTitle: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "popularity") var popularity: Double? = null,
    @ColumnInfo(name = "poster_path") var posterPath: String? = null,
    @ColumnInfo(name = "release_date") var releaseDate: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "video") var video: Boolean? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = null
)