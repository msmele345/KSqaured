package com.mitchmele.ksquared.repository.network

import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.utils.CoroutineTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AlgorithmRepositoryTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var algorithmRepository: AlgorithmRepository
    private lateinit var algorithmApi: AlgorithmApi


    private val listOfAlgorithms = listOf(
        Algorithm(
            name = "palindrome",
            codeSnippet = "fun palindrome(str1: String, str2: String) = str1.reverse() == str2 "
        ),
        Algorithm(
            name = "popLast",
            codeSnippet = "fun popLast(str1: String) = str1.drop(1)"
        )
    )
    private val successfulResponse = Response.success(listOfAlgorithms)

    @Before
    fun setUp() {
        algorithmApi = mock()
        algorithmRepository = AlgorithmRepository(algorithmApi)
        runBlocking {
            whenever(algorithmApi.getResponseAlgorithms()) doReturn successfulResponse
            whenever(algorithmApi.getAlgorithmByName(any())) doReturn Response.success(
                Algorithm(
                    name = "palindrome",
                    codeSnippet = "fun palindrome(str1: String, str2: String) = str1.reverse() == str2 "
                )
            )
        }
    }

    @Test
    fun getResponseAlgos_success_shouldReturnListOfAlgos() {
        runBlocking {
            val actual = algorithmRepository.getResponseAlgos()
            val expected = ResultData.success(listOfAlgorithms)
            assertThat(actual is ResultData.Success)
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun getResponseAlgos_failure_shouldReturnFailureWithHttpMessage() {
        //when
        runBlocking {
            whenever(algorithmApi.getResponseAlgorithms()) doReturn
                    Response.error(
                        500, ResponseBody.create(
                            "application/json".toMediaTypeOrNull(),
                            "{\"key\":[\"Bad ERROR\"]}"
                        )
                    )
        }
        //actual
        runBlocking {
            val actual = algorithmRepository.getResponseAlgos()
            assertThat(actual).isEqualTo(
                ResultData.failure<List<Algorithm>>(
                    "Network call has failed for a following reason: 500 Response.error()"
                )
            )
        }
    }

    @Test
    fun getAlgorithmByName_success_shouldReturnSpecificAlgorithm() {
        runBlocking {
            val actual = algorithmRepository.getAlgorithmByName("palindrome")
            val expected = ResultData.success(
                Algorithm(
                    name = "palindrome",
                    codeSnippet = "fun palindrome(str1: String, str2: String) = str1.reverse() == str2 "
                )
            )
            assertThat(actual is ResultData.Success)
            assertThat(actual).isEqualTo(expected)
        }
    }
}