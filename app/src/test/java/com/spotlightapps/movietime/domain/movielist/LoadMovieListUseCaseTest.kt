package com.spotlightapps.movietime.domain.movielist

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.movietime.MainCoroutineRule
import com.spotlightapps.movietime.fakes.FakeMovieRepository
import com.spotlightapps.movietime.model.Result
import com.spotlightapps.movietime.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

@ExperimentalCoroutinesApi
class LoadMovieListUseCaseTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun loadMovieList_successResult() = coroutineRule.runBlockingTest {
        //GIVEN
        val loadMovieListUseCase = LoadMovieListUseCase(
            FakeMovieRepository(),
            coroutineRule.testDispatcher
        )

        //WHEN
        val result = loadMovieListUseCase(true)

        //ThEN
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data[0].id).isEqualTo(1)
    }

    @Test
    fun loadMovieList_errorResult() = coroutineRule.runBlockingTest {
        //GIVEN
        val loadMovieListUseCase = LoadMovieListUseCase(
            FakeMovieRepository().apply { setThrowException(true) },
            coroutineRule.testDispatcher
        )

        //WHEN
        val result = loadMovieListUseCase(true)

        //ThEN
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).exception.message).isEqualTo("Network Error")
    }
}