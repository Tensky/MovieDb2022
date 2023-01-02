package id.tensky.core.data.mapper

import id.tensky.core.data.local.entity.DiscoverMovieEntity
import id.tensky.core.data.local.entity.TrendingMovieEntity
import id.tensky.core.data.remote.response.DiscoverMovieResponse
import id.tensky.core.data.remote.response.TrendingMovieResponse
import id.tensky.core.domain.model.Movie

object MovieListMapper {
    fun TrendingMovieResponse.toEntity(): TrendingMovieEntity {
        return TrendingMovieEntity(
            id ?: 0,
            adult,
            backdropPath,
            name,
            originalLanguage,
            originalName,
            overview,
            posterPath,
            mediaType,
            genreIds,
            popularity
        )
    }

    fun TrendingMovieEntity.toDomainModel(): Movie {
        return Movie(
            id,
            adult,
            name,
            originalLanguage,
            overview,
            posterPath,
            genreIds,
            popularity,
            firstAirDate,
            voteAverage,
            voteCount
        )
    }

    fun DiscoverMovieResponse.toEntity(): DiscoverMovieEntity {
        return DiscoverMovieEntity(
            id ?: 0,
            adult,
            backdropPath,
            genreIds,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            title,
            video,
            voteAverage,
            voteCount
        )
    }

    fun DiscoverMovieEntity.toDomainModel(): Movie {
        return Movie(
            id,
            adult,
            title,
            originalLanguage,
            overview,
            posterPath,
            genreIds ?: arrayListOf(),
            popularity,
            null,
            voteAverage,
            voteCount
        )
    }
}