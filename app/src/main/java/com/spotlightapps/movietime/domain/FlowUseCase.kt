package com.spotlightapps.movietime.domain

import com.spotlightapps.movietime.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

/**
 * Created by Ahmad Jawid Muhammadi
 * on 20-01-2022.
 */

abstract class FlowUseCase<in P, out R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: P): Flow<Result<R>> {
        return try {
            execute(parameters).flowOn(coroutineDispatcher)
        } catch (e: Exception) {
            flow { emit(Result.Error(e)) }
        }
    }

    abstract fun execute(parameters: P): Flow<Result<R>>
}