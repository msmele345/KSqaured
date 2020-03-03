package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.AlgorithmSummaryResponse
import com.mitchmele.ksquared.model.Algorithm
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlgorithmApi {

    @GET("http://localhost:8080/algorithms/all/")
    suspend fun getAlgorithms() : List<Algorithm>

    @GET(BASE_URL)
    suspend fun getResponseAlgorithms() : Response<List<Algorithm>>

    @GET("http://10.0.2.2:8080/algorithms/{name}/")
    suspend fun getAlgorithmByName(@Path("name")name: String): Response<Algorithm>
}