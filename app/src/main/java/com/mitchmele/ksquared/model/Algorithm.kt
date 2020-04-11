package com.mitchmele.ksquared.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson
import java.util.*


data class Algorithm(
//    var id: UUID? = null, // check, this works without it
    val name: String = "",
    val codeSnippet: String = "",
    val categoryDescription: String = "",
    val difficultyLevel: Int? = null,
    val categoryTags: String = "",
    val solved: Boolean = false
)

