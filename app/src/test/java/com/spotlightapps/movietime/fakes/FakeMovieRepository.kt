package com.spotlightapps.movietime.fakes

import com.spotlightapps.movietime.TestData
import com.spotlightapps.movietime.data.movie.MovieRepository
import com.spotlightapps.movietime.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

class FakeMovieRepository : MovieRepository {

    private var isToThrowException: Boolean = false

    override fun getMovieList(isToForceRemote: Boolean): Flow<List<Movie>> {
        if (isToThrowException) throw Exception("Network Error")
        val apiResponse = TestData.movieApiModel1
        return flow { emit(apiResponse.movieResults?.map { it.toMovieItem() }!!) }
    }

    fun setThrowException(isToThrowException: Boolean) {
        this.isToThrowException = isToThrowException
    }
}