package com.mitchmele.ksquared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.algo_store.getRandom
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.repository.network.AlgorithmRepository
import kotlinx.coroutines.Dispatchers

class AlgorithmViewModel : ViewModel() {

    private val algoRepository = AlgorithmRepository()
    //OR pass api to repository constructor and initialize like this:
    //    private val repository = AlgorithmRepository(ApiFactory.algoApi)

    //wraps response in live data automatically
    //BaseDataSource is managing the errorHandling
    val algorithmLiveDataRetro = liveData(Dispatchers.IO) {
        val currentAlgorithms = algoRepository.getResponseAlgos()
        emit(currentAlgorithms)
    }



/* DEBUG VIEWMODEL EXAMPLE W/O Retrofit
    val algorithmLiveData = liveData(Dispatchers.IO) {
        val algorithms: ResultData<MutableList<Algorithm>> = ResultData.success(algorithms) //retrofit call goes here
        emit(algorithms)
    }
    val algorithms = mutableListOf<Algorithm>()

    init {
        val names = listOf(
            "removeDupes",
            "stringParser",
            "countPrimes",
            "sumEvenNumbers",
            "onlyUniqueValues",
            "countOddValues"
        )
        val categoryDescriptions = listOf("HARD", "EASY", "MEDIUM")
        (0..25).forEach { i ->
            val algo = Algorithm(
                name = names.getRandom(),
                codeSnippet = "fun getSum(value1: Int, value2: Int) = value1 + value2",
                categoryDescription = categoryDescriptions.getRandom(),
                difficultyLevel = (1..5).toList().getRandom(),
                categoryTags = "Tag: Strings, Tag: Arrays Tag: Numbers",
                solved = i % 2 == 0

            )
            algorithms.add(algo)
        }
    }
 */
}