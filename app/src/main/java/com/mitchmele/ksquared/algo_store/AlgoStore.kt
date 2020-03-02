package com.mitchmele.ksquared.algo_store

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class AlgorithmSummaryResponse(
    val name: String = "",
    val codeSnippet: String = "",
    val categoryDescription: String = "",
    val difficultyLevel: Int? = null,
    val categoryTags: List<String> = emptyList()
)

//for setting ui
sealed class UIViewState {
    object Loading : UIViewState()
    object Empty: UIViewState()
    object UIError: UIViewState()
    object UISuccess: UIViewState()
}

//for managing response objects from retrofit and liveData
sealed class ResultData<out T> {

    data class Success<T>(val value: T): ResultData<T>()
    data class Failure<T>(val errorMessage: String): ResultData<T>()
    data class Loading<T>(val loadingMessage: T? = null): ResultData<T>()


    companion object {
        fun <T> loading(value: T? = null) : ResultData<T> = Loading(value)
        fun <T> success(value: T): ResultData<T> = Success(value)
        fun <T> failure(errorMessage: String): ResultData<T> = Failure(errorMessage)
    }
}


fun<T> List<T>.getRandom() : T {
    return this.shuffled().first()
}


interface DispatcherProvider {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

class DefaultDispatcherProvider(
    override val ui: CoroutineDispatcher = Dispatchers.Main,
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
) : DispatcherProvider
