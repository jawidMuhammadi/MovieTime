package com.spotlightapps.movietime.domain

import com.spotlightapps.movietime.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class CoroutineUseCase<in P, out R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        // Moving all use case's executions to the injected dispatcher
        // In production code, this is usually the Default dispatcher (background thread)
        // In tests, this becomes a TestCoroutineDispatcher
        return withContext(coroutineDispatcher) {
            try {
                val result = execute(parameters)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    /**
     * Override this to set the code be executed
     */
    abstract suspend fun execute(parameters: P): R
}