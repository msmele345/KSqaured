package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.algo_store.getRandom
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers
import java.util.*

class AlgorithmViewModel : ViewModel() {

    private val algoRepository = AlgorithmRepository()
    //OR pass api to repository constructor and initialize like this:
    //private val repository = AlgorithmRepository(ApiFactory.algoApi)

    //wraps response in live data automatically
    //BaseDataSource is managing the errorHandling
    val algorithmLiveDataRetro =
        liveData(Dispatchers.IO) {
            val currentAlgorithms = algoRepository.getResponseAlgos()
            emit(currentAlgorithms)
        }

}