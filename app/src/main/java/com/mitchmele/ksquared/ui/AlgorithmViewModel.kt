package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.repository.network.AlgorithmApi
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class AlgorithmViewModel(
    private val algorithmApi: AlgorithmApi
) : ViewModel(), NetworkClient {

    //wraps response in live data automatically
    val algorithmLiveDataRetro =
        liveData(Dispatchers.IO) {
            val currentAlgorithms = getData { algorithmApi.getResponseAlgorithms() }
            emit(currentAlgorithms)
        }

    override suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T> {
        call().let { response ->
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return ResultData.success(body)
            }
            return showError("${response.code()} ${response.message()}")
        }
    }

    override fun <T> showError(errorMessage: String): ResultData<T> {
        return ResultData.failure("Network call has failed for a following reason: $errorMessage")
    }
}


interface NetworkClient {
    suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T>
    fun <T> showError(errorMessage: String): ResultData<T>
}
