package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.AlgorithmSummaryResponse
import retrofit2.http.GET

interface AlgorithmApi {

    @GET("http://localhost:8080/algorithms/all")
    suspend fun getAlgorithms() : List<AlgorithmSummaryResponse>
}