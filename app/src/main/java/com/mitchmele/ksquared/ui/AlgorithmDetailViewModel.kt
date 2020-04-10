package com.mitchmele.ksquared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.NetworkClient
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class AlgorithmDetailViewModel : ViewModel() {

    private val algorithmRepository = AlgorithmRepository()

    fun getAlgorithmByName(name: String) =
            liveData(Dispatchers.IO) {
                val algorithm = algorithmRepository.getAlgorithmByName(name)
                emit(algorithm)
            }

}
