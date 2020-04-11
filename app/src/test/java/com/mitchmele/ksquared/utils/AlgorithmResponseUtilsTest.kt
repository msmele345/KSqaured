package com.mitchmele.ksquared.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlgorithmResponseUtilsTest {


    val subject = AlgorithmResponseUtils()


    @Test
    fun formatTags_success_shouldRemoveTagKeyWordsFromString() {
        val incomingCategories = "Tag: String Manipulation, Tag: String Formatting, Tag: Algorithms"

        val expected = "String Manipulation, String Formatting, Algorithms"
//        val actual = subject.Companion.formatCategoryTags(incomingCategories)

//        assertThat(actual).isEqualTo(expected)
    }
}