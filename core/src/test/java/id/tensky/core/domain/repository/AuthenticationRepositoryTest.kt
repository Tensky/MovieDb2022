package id.tensky.core.domain.repository

import id.tensky.core.data.remote.network.ApiResponse
import id.tensky.core.data.remote.response.GuestAuthenticationResponse
import id.tensky.core.data.remote.source.IAuthenticationRemoteSource
import id.tensky.core.data.repository.AuthenticationRepository
import id.tensky.core.util.resource.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class AuthenticationRepositoryTest {
    private val remoteSource = mockk<IAuthenticationRemoteSource>(relaxed = true)
    private val apiKey = "TEST"
    private val repository = AuthenticationRepository(apiKey, remoteSource)

    private val TEST_SUCCESS = "test_success"
    private val TEST_FAILED = "test_failed"

    @Before
    fun setup() {
        coEvery { remoteSource.createGuestSession(apiKey) } returns ApiResponse.Success(
            GuestAuthenticationResponse(guestSessionId = TEST_SUCCESS)
        )
    }

    @Test
    fun `test Guest Authentication`() {
        runBlocking {
            val result = repository.authenticateGuest()
            coVerify { remoteSource.createGuestSession(apiKey) }
            Assert.assertEquals(true, result is Resource.Success)
            if(result is Resource.Success){
                Assert.assertEquals(TEST_SUCCESS, result.data.guestSessionId)
            }
        }
    }
}