package com.mitchmele.ksquared.algo_store


data class AlgorithmSummaryResponse(
    val name: String = "",
    val codeSnippet: String = "",
    val categoryDescription: String = "",
    val difficultyLevel: Int? = null,
    val categoryTags: List<String> = emptyList()
)

sealed class UIViewState {
    object Loading : UIViewState()
    object Empty: UIViewState()
    data class UIError(val message: String? = null) : UIViewState()
    data class UISuccess<T>(val content: T, val dataExtras: String? = null)
}


sealed class Try<T> {

    companion object {
        operator fun <T> invoke(func: () -> T): Try<T> =
            try {
                Success(func())
            } catch (ex: Exception) {
                Failure(ex)
            }
    }

    abstract fun <R> map(transform: (T) -> R): Try<R>
    abstract fun <R> flatMap(transform: (T) -> Try<R>): Try<R>

    abstract fun <R> recover(transform: (Exception) -> R): Try<R>
    abstract fun <R> recoverWith(transform: (Exception) -> Try<R>): Try<R>

}


data class Success<T>(val value: T) : Try<T>() {
    override fun <R> map(transform: (T) -> R): Try<R> =
        Try { transform(value) }

    override fun <R> flatMap(transform: (T) -> Try<R>): Try<R> {

        return Try { transform(value) }.let {
            when (it) {
                is Success -> it.value
                is Failure -> it as Try<R>
            }
        }
    }

    override fun <R> recover(transform: (Exception) -> R): Try<R> = this as Try<R>

    override fun <R> recoverWith(transform: (Exception) -> Try<R>): Try<R> = this as Try<R>

    override fun toString(): String {
        return "Success(${value.toString()})"
    }
}


data class Failure<T>(val error: java.lang.Exception, val message: String = "") : Try<T>() {
    override fun <R> map(transform: (T) -> R): Try<R> = this as Try<R>

    override fun <R> flatMap(transform: (T) -> Try<R>): Try<R> = this as Try<R>

    override fun <R> recover(transform: (Exception) -> R): Try<R> =
        Try { transform(error) }

    override fun <R> recoverWith(transform: (Exception) -> Try<R>): Try<R> =
        Try { transform(error) }.let {
            when (it) {
                is Success -> it.value
                is Failure -> it as Try<R>
            }
        }

    override fun toString(): String {
        return "Failure(${error.message})"
    }
}