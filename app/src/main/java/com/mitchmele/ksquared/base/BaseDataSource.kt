package com.mitchmele.ksquared.base

import com.mitchmele.ksquared.algo_store.ResultData
import retrofit2.Response

interface NetworkClient {
    suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T>
    fun <T> showError(errorMessage: String): ResultData<T>
}

abstract class BaseDataSource : NetworkClient {

    override suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T> {
        call().let { response ->
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return ResultData.success(body)
            } else {
                return showError("${response.code()} ${response.message()}")

            }
            return showError("${response.code()} ${response.message()}")
        }
    }

    override fun <T> showError(errorMessage: String): ResultData<T> {
        return ResultData.failure("Network call has failed for a following reason: $errorMessage")
    }
}
