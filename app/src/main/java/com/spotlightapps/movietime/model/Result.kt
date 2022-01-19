package com.spotlightapps.movietime.model

import java.lang.Exception

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data = $data]"
            is Error -> "Error [error $exception"
            else -> "Loading"
        }
    }
}

val Result<*>.Succeeded
    get() = this is Result.Success && data != null