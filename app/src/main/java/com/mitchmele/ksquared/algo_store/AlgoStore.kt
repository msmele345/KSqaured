package com.mitchmele.ksquared.algo_store

import com.mitchmele.ksquared.model.Algorithm


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


fun generateAlgorithm(): List<Algorithm> {
    val names = listOf("removeDupes", "stringParser", "countPrimes", "sumEvenNumbers", "onlyUniqueValues", "countOddValues")
    val categoryDescriptions = listOf("HARD", "EASY", "MEDIUM")
    return (0..25).map {
        Algorithm(
            name = names.getRandom(),
            codeSnippet = "fun getSum(value1: Int, value2: Int) = value1 + value2",
            categoryDescription = categoryDescriptions.getRandom(),
            difficultyLevel = (1..5).toList().getRandom(),
            categoryTags = "Strings, Arrays, Numbers"

        )
    }
}



fun<T> List<T>.getRandom() : T {
    return this.shuffled().first()
}
