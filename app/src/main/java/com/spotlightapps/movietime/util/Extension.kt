package com.spotlightapps.movietime.util

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

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

fun ProgressBar.showProgressBar(isToShow: Boolean) {
    this.visibility = if (isToShow) View.VISIBLE else View.GONE
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    mainActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline codeBlock: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(mainActiveState) {
            codeBlock()
        }
    }
}

fun Fragment.showToastMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
