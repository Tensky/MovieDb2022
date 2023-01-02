package id.tensky.core.data.remote.network

import id.tensky.core.util.constants.BaseUrlConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBase {
    @Volatile
    private var _instance: Retrofit? = null

    fun getInstance(): Retrofit =
        _instance ?: synchronized(this) {
            val instance = Retrofit.Builder()
                .baseUrl(BaseUrlConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
            _instance = instance
            instance
        }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}