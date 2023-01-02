package id.tensky.core.data.remote.service

import id.tensky.core.data.remote.response.GuestAuthenticationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationEndpoint {
    @GET("/3/authentication/guest_session/new")
    suspend fun createGuestSession(
        @Query("api_key") apiKey: String
    ): Response<GuestAuthenticationResponse>
}