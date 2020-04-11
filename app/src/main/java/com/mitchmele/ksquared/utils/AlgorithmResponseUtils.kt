package com.mitchmele.ksquared.utils

class AlgorithmResponseUtils {


    companion object {
        fun formatCategoryTags(tags: String) : String {
            return tags.split(" ").filter { it != "Tag:" }.joinToString(" ")
        }
    }


}