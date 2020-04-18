package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.NetworkClient
import com.mitchmele.ksquared.repository.network.AlgorithmApi
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class AlgorithmDetailViewModel(
    private val algorithmRepository: AlgorithmRepository
) : ViewModel() {

    fun getAlgorithmByName2(name: String) =
        liveData(Dispatchers.IO) {
            val algorithm = algorithmRepository.getAlgorithmByName(name)
            emit(algorithm)
        }
}