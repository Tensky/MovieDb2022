package id.tensky.core.domain.model

data class Movie(
    var id: Int,
    var adult: Boolean? = null,
    var name: String? = null,
    var originalLanguage: String? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var genreIds: ArrayList<Int> = arrayListOf(),
    var popularity: Double? = null,
    var firstAirDate: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)