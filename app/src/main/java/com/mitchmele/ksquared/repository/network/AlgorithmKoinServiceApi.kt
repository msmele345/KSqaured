package com.mitchmele.ksquared.repository.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class AlgorithmKoinServiceApi {

    fun providesApiService(): AlgorithmApi =
        Retrofit.Builder()
            .client(algoClient)
            .addConverterFactory(
                MoshiConverterFactory.create().asLenient()
            )
            .build()
            .create(AlgorithmApi::class.java)



    private val loggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    private val algoClient: OkHttpClient = OkHttpClient().newBuilder()
        .run {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            build()
        }


    private fun retroFit(url: String): Retrofit {
        return Retrofit.Builder()
            .client(algoClient)
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create().asLenient()
            )
            .build()

    }

}

//add this to constructor of repository and add to koin
