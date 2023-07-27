package com.codebaron.sharedlogc

import com.codebaron.sharedlogc.internet.InternetConfiguration
import com.codebaron.sharedlogc.remote.BaseEndpoints
import com.codebaron.sharedlogc.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.codebaron.sharedlogc.BuildConfig

@Module @InstallIn(SingletonComponent::class)
object HiltInjection {

    @Singleton @Provides
    fun provideInternetInjection() = InternetConfiguration()

    @Singleton @Provides
    fun provideEndPoints(retrofit: Retrofit) = retrofit.create(BaseEndpoints::class.java)

    @Singleton @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton @Provides
    fun provideOKHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor).addInterceptor{
                chain -> val request = chain.request().newBuilder().build()
                chain.proceed(request)
            }
        }.build()

    @Singleton @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(BuildConfig.BASE_URL)
        client(okHttpClient)
    }.build()
}