package com.spotlightapps.movietime.data.movie

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.movietime.MainCoroutineRule
import com.spotlightapps.movietime.TestData
import com.spotlightapps.movietime.data.network.MovieService
import com.spotlightapps.movietime.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ahmad Jawid Muhammadi
 * on 18-01-2022.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DefaultMovieRepositoryTest {

    @get: Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    lateinit var movieService: MovieService

    lateinit var movieRepository: DefaultMovieRepository

    @Before
    fun setup() = coroutineRule.runBlockingTest {
        movieRepository = DefaultMovieRepository(movieService)
        Mockito.`when`(movieService.movieList()).thenReturn(TestData.movieApiModel1)
    }

    @Test
    fun getMovieList_returnsCorrectList() = coroutineRule.runBlockingTest {
        val list = movieRepository.getMovieList(false)

        assertThat(list.first().size).isEqualTo(1)
        assertThat(list.first()[0].title).isEqualTo(TestData.result1.title)
        assertThat(list.first()[0].voteAverage).isInstanceOf(String::class.java)
    }
}