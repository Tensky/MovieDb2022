package id.tensky.core.data.remote.source

import android.util.Log
import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.response.GuestAuthenticationResponse
import id.tensky.core.data.remote.service.AuthenticationEndpoint
import kotlin.math.log

interface IAuthenticationRemoteSource {
    suspend fun createGuestSession(apiToken: String): ApiResponse<GuestAuthenticationResponse>
}

class AuthenticationRemoteSource(private val endpoint: AuthenticationEndpoint) :
    IAuthenticationRemoteSource {
    override suspend fun createGuestSession(apiToken: String): ApiResponse<GuestAuthenticationResponse> {
        Log.d("TAG", "createGuestSession: ")
        return try {
            val response = endpoint.createGuestSession(apiToken)
            Log.d("TAG", "createGuestSession: ${response}")
            if (response.isSuccessful && response.body() != null) {
                (ApiResponse.Success(response.body()!!))
            } else {
                (ApiResponse.Empty)
            }
        } catch (e: Exception) {
            (ApiResponse.Error(e.toString()))
        }
    }
}