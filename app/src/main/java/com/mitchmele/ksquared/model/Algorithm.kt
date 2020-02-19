package com.mitchmele.ksquared.model

data class Algorithm(
    val name: String = "",
    val codeSnippet: String = "",
    val categoryDescription: String = "",
    val difficultyLevel: Int? = null,
    val categoryTags: List<String> = emptyList()
)