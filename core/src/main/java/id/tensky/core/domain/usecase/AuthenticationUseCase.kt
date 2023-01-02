package id.tensky.core.domain.usecase

import id.tensky.core.domain.model.AuthenticationSession
import id.tensky.core.domain.repository.IAuthenticationRepository
import id.tensky.core.util.resource.Resource

interface AuthenticationUseCase {
    suspend fun authenticateGuest(): Resource<AuthenticationSession>
}

class AuthenticationInteractor(private val repository: IAuthenticationRepository) :
    AuthenticationUseCase {
    override suspend fun authenticateGuest(): Resource<AuthenticationSession> {
        return repository.authenticateGuest()
    }
}