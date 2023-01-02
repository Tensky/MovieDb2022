package id.tensky.core.data.remote.service

import id.tensky.core.data.remote.response.DiscoverMovieResponse
import id.tensky.core.data.remote.response.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailEndpoint {
    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailResponse>
}