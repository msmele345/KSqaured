package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.ResultData
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): ResultData<T> {
        return ResultData.success(data)
    }

    fun <T : Any> handleException(e: Exception): ResultData<T> {
        return when (e) {
            is HttpException -> ResultData.failure<T>(getErrorMessage(e.code()))
            is SocketTimeoutException -> ResultData.failure<T>(getErrorMessage(1))
            else -> ResultData.failure<T>(getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            1 -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}