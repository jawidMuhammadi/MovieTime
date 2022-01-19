package com.spotlightapps.movietime.fakes

import com.spotlightapps.movietime.TestData
import com.spotlightapps.movietime.data.movie.MovieRepository
import com.spotlightapps.movietime.model.Movie

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

class FakeMovieRepository : MovieRepository {

    private var isToThrowException: Boolean = false

    override suspend fun getMovieList(isToForceRemote: Boolean): List<Movie> {
        if (isToThrowException) throw Exception("Network Error")
        val apiResponse = TestData.movieApiModel1
        return apiResponse.movieResults?.map { it.getMovieItem() }!!
    }

    fun setThrowException(isToThrowException: Boolean) {
        this.isToThrowException = isToThrowException
    }
}