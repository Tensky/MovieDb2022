package id.tensky.core.util.resource

sealed class Resource<T>() {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
    class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}