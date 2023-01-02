package id.tensky.core.domain.repository

import id.tensky.core.domain.model.AuthenticationSession
import id.tensky.core.util.constants.ApiKeyConstant
import id.tensky.core.util.resource.Resource

interface IAuthenticationRepository {
    suspend fun authenticateGuest(): Resource<AuthenticationSession>
}