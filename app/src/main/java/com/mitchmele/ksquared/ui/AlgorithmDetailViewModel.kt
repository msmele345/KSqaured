package com.mitchmele.ksquared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.base.NetworkClient
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.repository.network.AlgorithmApi
import com.mitchmele.ksquared.repository.network.AlgorithmKoinServiceApi
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
//constructor will soon have new koin api
class AlgorithmDetailViewModel : ViewModel(), NetworkClient {

    private val algorithmRepository = AlgorithmRepository()

    fun getAlgorithmByName(name: String) =
        liveData(Dispatchers.IO) {
            val algorithm = algorithmRepository.getAlgorithmByName(name)
            emit(algorithm)
        }


    fun getAlgorithmByName2(name: String) =
        liveData(Dispatchers.IO) {
            val algorithm = algorithmRepository.getAlgorithmByName(name)
            emit(algorithm)
        }

//    suspend fun fetchAlgorithmByName(name: String): ResultData<Algorithm> {
//        return getData { koinApi.getAlgorithmByName(name) }
//    }

    override suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T> {
        TODO("Not yet implemented")
    }

    override fun <T> showError(errorMessage: String): ResultData<T> {
        TODO("Not yet implemented")
    }
}


//Add API to constructor of view models
//Remove repository
//Move repo methods here:
//Update Views for detail with layouts