package id.tensky.core.data.mapper

import id.tensky.core.data.remote.response.GuestAuthenticationResponse
import id.tensky.core.domain.model.AuthenticationSession

object AuthenticationMapper {
    fun GuestAuthenticationResponse.toDomainModel(): AuthenticationSession {
        return AuthenticationSession(
            guestSessionId ?: ""
        )
    }
}