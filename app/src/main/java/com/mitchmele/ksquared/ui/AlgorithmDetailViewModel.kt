package com.mitchmele.ksquared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers

class AlgorithmDetailViewModel : ViewModel() {

    private val algorithmRepository = AlgorithmRepository()
    private val algoIdLiveData = MutableLiveData<String>()

    var algorithmName: String? = null

//    val getAlgorithmByName =
//        liveData(Dispatchers.IO) {
//            val algorithm = algorithmRepository.getAlgorithmByName(name)
//            emit(algorithm)
//        }


    fun getAlgorithmByName(name: String) =
            liveData(Dispatchers.IO) {
                val algorithm = algorithmRepository.getAlgorithmByName(name)
                emit(algorithm)
            }



    fun loadAlgortithm(algorithmId: String?) {
        algoIdLiveData.value = algorithmId
    }
}
