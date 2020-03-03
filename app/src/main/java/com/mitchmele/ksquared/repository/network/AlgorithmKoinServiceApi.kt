package com.mitchmele.ksquared.repository.network

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