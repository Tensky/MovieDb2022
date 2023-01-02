package id.tensky.core.domain.model


data class MovieDetail(
    var id: Int,
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var revenue: Long? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
)