package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.BaseDataSource
import com.mitchmele.ksquared.model.Algorithm

class AlgorithmRepository: BaseDataSource() {

    var client =  ApiFactory.webservice

   suspend fun getAlgorithms(): List<Algorithm> {
        return client.getAlgorithms()
    }

    suspend fun getResponseAlgos(): ResultData<List<Algorithm>> {
        return getData { client.getResponseAlgorithms() }
    }
}