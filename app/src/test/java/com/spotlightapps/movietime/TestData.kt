package com.spotlightapps.movietime

import com.spotlightapps.movietime.model.MovieApiModel
import com.spotlightapps.movietime.model.Result

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

object TestData {
    val result1 = Result(
        id = 1,
        title = "Title1",
        posterPath = "path1",
        voteAverage = 3.5,
        voteCount = 5
    )

    val movieApiModel1 = MovieApiModel(
        page = 1,
        results = listOf(result1),
        totalPages = 1
    )
}