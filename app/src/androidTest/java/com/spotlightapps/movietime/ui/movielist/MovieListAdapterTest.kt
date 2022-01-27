package com.spotlightapps.movietime.ui.movielist

import com.google.common.truth.Truth.assertThat
import com.spotlightapps.movietime.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-01-2022.
 */

@ExperimentalCoroutinesApi
class MovieListAdapterTest {

    @Test
    fun testMovieList_isEmpty() {
        val adapter = MovieListAdapter()

        assertThat(adapter.currentList.size).isEqualTo(0)
    }

    @Test
    fun testSubmitItemList() {
        val adapter = MovieListAdapter().apply {
            submitList(
                listOf(
                    Movie(
                        id = 1,
                        title = "Title1",
                        posterPath = "path1",
                        voteAverage = "3.5",
                        voteCount = "5"
                    )
                )
            )
        }
        assertThat(adapter.itemCount).isEqualTo(1)
        assertThat(adapter.currentList[0].id).isEqualTo(1)
    }
}