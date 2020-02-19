package com.mitchmele.ksquared.repository.network

import retrofit2.Retrofit

object RetrofitClient {

    val service by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080/algorithms/all")
            .build()
    }

    val api = service.create(AlgorithmApi::class.java)

}