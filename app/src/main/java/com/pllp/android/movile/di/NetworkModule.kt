package com.pllp.android.movile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module le dice a Dagger / Hilt que esa clase (o object) contiene funciones que proveen dependencias.
@Module
//Se crea una sola instancia y se comparte en toda la app.
@InstallIn(SingletonComponent::class)

object NetworkModule {

    private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "93afb7df8ce819932f9815f25e5cc925"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val authInterceptor = Interceptor { chain ->
            val original = chain.request()
            val url = original.url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", "es-ES")
                .build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val loggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor).connectTimeout(
                30,
                TimeUnit.SECONDS
            ).readTimeout(30, TimeUnit.SECONDS).build()

        return Retrofit.Builder().baseUrl(TMDB_BASE_URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }
}