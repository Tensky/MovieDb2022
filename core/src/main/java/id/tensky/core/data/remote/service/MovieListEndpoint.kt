package id.tensky.core.data.remote.service

import id.tensky.core.data.remote.response.DiscoverMovieResponse
import id.tensky.core.data.remote.response.TrendingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListEndpoint {
    @GET("/3/trending/all/day")
    suspend fun getTrendingMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Number,
    ): Response<TrendingMovieResponse.Wrapper>

    @GET("/3/discover/movie")
    suspend fun getDiscoverMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Number,
    ): Response<DiscoverMovieResponse.Wrapper>
}