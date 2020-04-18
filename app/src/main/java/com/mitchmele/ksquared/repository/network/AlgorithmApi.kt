package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.utils.KSquaredConstants.BASE_URL
import com.mitchmele.ksquared.utils.KSquaredConstants.NAME_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlgorithmApi {

    @GET(BASE_URL)
    suspend fun getResponseAlgorithms() : Response<List<Algorithm>>

    @GET(NAME_URL)
    suspend fun getAlgorithmByName(@Path("name")name: String): Response<Algorithm>
}