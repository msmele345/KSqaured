package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.AlgorithmSummaryResponse
import com.mitchmele.ksquared.model.Algorithm
import retrofit2.Response
import retrofit2.http.GET

interface AlgorithmApi {

    @GET("http://localhost:8080/algorithms/all/")
    suspend fun getAlgorithms() : List<Algorithm>

    @GET(BASE_URL)
    suspend fun getResponseAlgorithms() : Response<List<Algorithm>>
}