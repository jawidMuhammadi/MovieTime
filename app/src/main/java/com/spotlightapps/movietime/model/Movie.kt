package com.spotlightapps.movietime.model

/**
 * Created by Ahmad Jawid Muhammadi
 * on 18-01-2022.
 */

data class Movie(
    var id: Int? = null,
    var title: String? = null,
    var posterPath: String? = null,
    var voteAverage: String? = null,
    var voteCount: String? = null
)
