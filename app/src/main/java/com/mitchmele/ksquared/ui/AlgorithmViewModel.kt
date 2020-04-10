package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.algo_store.getRandom
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.repository.network.AlgorithmKoinServiceApi
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import java.util.*

class AlgorithmViewModel(
    private val algoRepository: AlgorithmRepository
) : ViewModel() {

    //wraps response in live data automatically
    //BaseDataSource is managing the errorHandling
    val algorithmLiveDataRetro =
        liveData(Dispatchers.IO) {
            val currentAlgorithms = algoRepository.getResponseAlgos()
            emit(currentAlgorithms)
        }

}