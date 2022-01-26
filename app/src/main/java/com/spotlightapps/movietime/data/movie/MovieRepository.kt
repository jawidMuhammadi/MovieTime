package com.spotlightapps.movietime.data.movie

import androidx.annotation.VisibleForTesting
import com.spotlightapps.movietime.data.network.MovieService
import com.spotlightapps.movietime.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by Ahmad Jawid Muhammadi
 * on 18-01-2022.
 */

interface MovieRepository {
    fun getMovieList(isToForceRemote: Boolean = false): Flow<List<Movie>>
}

class DefaultMovieRepository(
    private val movieService: MovieService
) : MovieRepository {

    @VisibleForTesting
    val movieList = mutableListOf<Movie>()

    /** Prevent local variables to be modified by different threads*/
    private val mutex = Mutex()

    override fun getMovieList(isToForceRemote: Boolean): Flow<List<Movie>> = flow {
        if (isToForceRemote || movieList.isEmpty()) {
            val results = movieService.movieList().movieResults
            results?.map { it.toMovieItem() }?.let {
                mutex.withLock {
                    movieList.addAll(it)
                }
            }
        }
        emit(mutex.withLock { movieList })
    }
}