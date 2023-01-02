package id.tensky.core.data.mapper

import id.tensky.core.data.local.entity.MovieDetailEntity
import id.tensky.core.data.remote.response.MovieDetailResponse
import id.tensky.core.domain.model.MovieDetail


object MovieDetailMapper {
    fun MovieDetailResponse.toEntity(): MovieDetailEntity {
        return MovieDetailEntity(
            id ?: 0,
            adult,
            backdropPath,
            budget,
            homepage,
            imdbId,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            revenue,
            runtime,
            status,
            tagline,
            title,
            video,
            voteAverage,
            voteCount
        )
    }

    fun toDomainModel(item: MovieDetailEntity): MovieDetail {
        item.apply {
            return MovieDetail(
                id,
                adult,
                backdropPath,
                budget,
                homepage,
                imdbId,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                posterPath,
                releaseDate,
                revenue,
                runtime,
                status,
                tagline,
                title,
                video,
                voteAverage,
                voteCount
            )
        }
    }
}