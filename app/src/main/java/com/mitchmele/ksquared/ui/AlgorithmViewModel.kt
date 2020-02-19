package com.mitchmele.ksquared.ui

import android.nfc.Tag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mitchmele.ksquared.model.Algorithm
import kotlinx.coroutines.Dispatchers

class AlgorithmViewModel : ViewModel() {

    val algorithmLiveData = liveData(Dispatchers.IO) {
        val algorithms = algorithms
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
        (0..25).forEach {
            val algo = Algorithm(
                name = names.getRandom(),
                codeSnippet = "fun getSum(value1: Int, value2: Int) = value1 + value2",
                categoryDescription = categoryDescriptions.getRandom(),
                difficultyLevel = (1..5).toList().getRandom(),
                categoryTags = listOf("Strings", "Arrays", "Numbers")

            )
            algorithms.add(algo)
        }
    }
}




fun<T> List<T>.getRandom() : T {
    return this.shuffled().first()
}


/*
*
*
*     private fun generateAlgorithm(): List<Algorithm> {
        val names = listOf("removeDupes", "stringParser", "countPrimes", "sumEvenNumbers", "onlyUniqueValues", "countOddValues")
        val categoryDescriptions = listOf("HARD", "EASY", "MEDIUM")
        return (0..25).map {
            Algorithm(
                name = names.getRandom(),
                codeSnippet = "fun getSum(value1: Int, value2: Int) = value1 + value2",
                categoryDescription = categoryDescriptions.getRandom(),
                difficultyLevel = (1..5).toList().getRandom(),
                categoryTags = listOf("Strings", "Arrays", "Numbers")

            )
        }
    }
*
*
* */