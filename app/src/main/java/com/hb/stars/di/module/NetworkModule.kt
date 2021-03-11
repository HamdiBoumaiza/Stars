package com.hb.stars.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.hb.stars.BuildConfig
import com.hb.stars.data.commun.BASE_URL
import com.hb.stars.data.datasource.remote.StarWarsServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        const val IO_TIMEOUT = 30L
    }

    @Provides
    @Singleton
    fun providesRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(StethoInterceptor())
        return client.build()
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): StarWarsServices =
            retrofit.create(StarWarsServices::class.java)
}
