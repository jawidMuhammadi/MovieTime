package com.spotlightapps.movietime.util

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Ahmad Jawid Muhammadi
 * on 27-01-2022.
 */

inline fun <T> MutableStateFlow<T>.update(function: (T) -> T) {
    while (true) {
        val prevValue = value
        val nextValue = function(prevValue)
        if (compareAndSet(prevValue, nextValue)) {
            return
        }
    }
}