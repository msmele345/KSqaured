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
//setup onClick for detail
//switch string back to UUID
//finish repo loader for UUID

class MoshiUUIDAdapter {

    @FromJson
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @ToJson
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }


    //private val moshi = Moshi.Builder()
//        .add(MoshiUUIDAdapter::class.java)
//        .build()

//    val uuidAdapter = moshi.adapter(Algorithm::class.java).
    //add auth interceptor here later if needed
}