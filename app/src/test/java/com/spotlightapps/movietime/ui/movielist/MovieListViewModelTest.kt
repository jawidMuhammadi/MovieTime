package com.spotlightapps.movietime.ui.movielist

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.movietime.MainCoroutineRule
import com.spotlightapps.movietime.TestData
import com.spotlightapps.movietime.domain.movielist.LoadMovieListUseCase
import com.spotlightapps.movietime.fakes.FakeMovieRepository
import com.spotlightapps.movietime.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 27-01-2022.
 */

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    private lateinit var viewModel: MovieListViewModel

    @get: Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `check if movieList item receives data`() = coroutineRule.runBlockingTest {
        viewModel = MovieListViewModel(
            LoadMovieListUseCase(
                FakeMovieRepository(),
                coroutineRule.testDispatcher
            )
        )
        val movieList = viewModel.uiState.first().movieList

        assertThat(movieList.size).isEqualTo(1)
        assertThat(movieList[0].title).isEqualTo(TestData.result1.title)
    }

    @Test
    fun `test API when it fails`() = coroutineRule.runBlockingTest {
        viewModel = MovieListViewModel(
            LoadMovieListUseCase(
                FakeMovieRepository().apply { setThrowException(true) },
                coroutineRule.testDispatcher
            )
        )

        assertThat(viewModel.uiState.value.message).isEqualTo("Network Error")
    }
}