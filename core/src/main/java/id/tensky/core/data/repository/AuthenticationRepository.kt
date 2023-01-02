package id.tensky.core.data.repository

import id.tensky.core.data.mapper.AuthenticationMapper.toDomainModel
import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.source.IAuthenticationRemoteSource
import id.tensky.core.domain.model.AuthenticationSession
import id.tensky.core.domain.repository.IAuthenticationRepository
import id.tensky.core.util.constants.ApiKeyConstant
import id.tensky.core.util.resource.Resource

class AuthenticationRepository(
    private val apiKey: String = ApiKeyConstant.API_KEY,
    private val remoteSource: IAuthenticationRemoteSource
) : IAuthenticationRepository {
    override suspend fun authenticateGuest(): Resource<AuthenticationSession> {
        return when (val result = remoteSource.createGuestSession(apiKey)) {
            is ApiResponse.Success -> {
                Resource.Success(result.data.toDomainModel())
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Empty -> {
                Resource.Error("System Error")
            }
        }
    }
}