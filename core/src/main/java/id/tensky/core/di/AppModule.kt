package id.tensky.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.tensky.core.data.local.db.MovieDatabase
import id.tensky.core.data.remote.network.RetrofitBase
import id.tensky.core.util.constants.ApiKeyConstant
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @Named("baseApi")
    fun provideBaseApi(
    ): Retrofit {
        return RetrofitBase.getInstance()
    }

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(
    ): String {
        return ApiKeyConstant.API_KEY
    }

    @Provides
    @Singleton
    @Named("movieDatabase")
    fun provideGithubDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase {
        return MovieDatabase.getInstance(context)
    }


}