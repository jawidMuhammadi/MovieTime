package com.spotlightapps.movietime

import com.spotlightapps.movietime.model.MovieApiModel
import com.spotlightapps.movietime.model.MovieResult

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

object TestData {
    val result1 = MovieResult(
        id = 1,
        title = "Title1",
        posterPath = "path1",
        voteAverage = 3.5,
        voteCount = 5
    )

    val movieApiModel1 = MovieApiModel(
        page = 1,
        movieResults = listOf(result1),
        totalPages = 1
    )
}