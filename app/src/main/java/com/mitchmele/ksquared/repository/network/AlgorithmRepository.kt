package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.DispatcherProvider
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.BaseDataSource
import com.mitchmele.ksquared.model.Algorithm

class AlgorithmRepository(
    private var algorithmApi: AlgorithmApi
) : BaseDataSource() {

    suspend fun getResponseAlgos(): ResultData<List<Algorithm>> {
        return getData { algorithmApi.getResponseAlgorithms() }
    }

    suspend fun getAlgorithmByName(name: String): ResultData<Algorithm> {
        return getData { algorithmApi.getAlgorithmByName(name) }
    }
}