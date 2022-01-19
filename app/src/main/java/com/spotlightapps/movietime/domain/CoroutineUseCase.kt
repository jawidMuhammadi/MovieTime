package com.spotlightapps.movietime.domain

import com.spotlightapps.movietime.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

abstract class CoroutineUseCase<in P, out R> constructor(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return withContext(coroutineDispatcher) {
            try {
                val result = execute(parameters)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    abstract suspend fun execute(parameters: P): R
}