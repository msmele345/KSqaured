package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.BaseDataSource
import com.mitchmele.ksquared.model.Algorithm

class AlgorithmRepository : BaseDataSource() {

    var client = ApiFactory.algoApi

    suspend fun getResponseAlgos(): ResultData<List<Algorithm>> {
        return getData { client.getResponseAlgorithms() }
    }

    suspend fun getAlgorithmByName(name: String): ResultData<Algorithm> {
        return getData { client.getAlgorithmByName(name) }
    }
}