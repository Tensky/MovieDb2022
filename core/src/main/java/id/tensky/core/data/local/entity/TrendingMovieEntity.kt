package id.tensky.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movie")
class TrendingMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "adult") var adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "original_language") var originalLanguage: String? = null,
    @ColumnInfo(name = "original_name") var originalName: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "poster_path") var posterPath: String? = null,
    @ColumnInfo(name = "media_type") var mediaType: String? = null,
    @ColumnInfo(name = "genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @ColumnInfo(name = "popularity") var popularity: Double? = null,
    @ColumnInfo(name = "first_air_date") var firstAirDate: String? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = null,
    @ColumnInfo(name = "origin_country") var originCountry: ArrayList<String> = arrayListOf()
)