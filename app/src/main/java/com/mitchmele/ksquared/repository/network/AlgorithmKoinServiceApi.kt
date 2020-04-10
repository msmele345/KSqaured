package com.mitchmele.ksquared.repository.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AlgorithmKoinServiceApi {


    fun getApiService(): AlgorithmApi {
        val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(AlgorithmApi::class.java)
    }

}

//add this to constructor of repository and add to koin
