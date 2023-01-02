package id.tensky.moviedb_2022.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tensky.core.domain.usecase.AuthenticationUseCase
import id.tensky.core.util.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticationUseCase: AuthenticationUseCase) :
    ViewModel() {
    val authenticationStateFlow =
        MutableStateFlow<AuthenticationPageState>(AuthenticationPageState.Empty)

    fun authenticateGuest() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = authenticationUseCase.authenticateGuest()) {
                is Resource.Success -> {
                    authenticationStateFlow.value = AuthenticationPageState.AuthenticationSuccess
                }
                is Resource.Error -> {
                    authenticationStateFlow.value =
                        AuthenticationPageState.Error(result.message)
                }
                else -> {

                }
            }
        }
    }
}