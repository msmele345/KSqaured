package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers

class AlgorithmViewModel(
    private val algorithmRepository: AlgorithmRepository
) : ViewModel() {

    val algorithmLiveDataRetro =
        liveData(Dispatchers.IO) {
            val currentAlgorithms =  algorithmRepository.getResponseAlgos()
            emit(currentAlgorithms)
        }
}
