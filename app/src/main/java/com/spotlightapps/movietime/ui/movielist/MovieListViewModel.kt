package com.spotlightapps.movietime.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotlightapps.movietime.domain.movielist.LoadMovieListUseCase
import com.spotlightapps.movietime.model.Movie
import com.spotlightapps.movietime.model.Result
import com.spotlightapps.movietime.util.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    loadMovieListUseCase: LoadMovieListUseCase
) : ViewModel() {


    private var _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isDataLoading = true) }
            loadMovieListUseCase(true).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isDataLoading = false,
                                movieList = result.data
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(message = result.exception.message)
                        }
                    }
                }
            }
        }
    }
}

data class MovieUiState(
    var isDataLoading: Boolean? = null,
    var movieList: List<Movie> = emptyList(),
    var message: String? = null
)