package id.tensky.moviedb_2022.ui.authentication

sealed class AuthenticationPageState {
    object AuthenticationSuccess: AuthenticationPageState()
    object Empty: AuthenticationPageState()
    data class Error(val errorMessage: String): AuthenticationPageState()
}