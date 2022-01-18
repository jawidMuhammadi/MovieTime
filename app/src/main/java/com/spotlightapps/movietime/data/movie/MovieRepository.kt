package com.spotlightapps.movietime.data.movie

import androidx.annotation.VisibleForTesting
import com.spotlightapps.movietime.data.network.MovieService
import com.spotlightapps.movietime.model.Movie
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by Ahmad Jawid Muhammadi
 * on 18-01-2022.
 */

interface MovieRepository {
    suspend fun getMovieList(isToForceRemote: Boolean = false): List<Movie>
}

class DefaultMovieRepository(
    private val movieService: MovieService
) : MovieRepository {

    @VisibleForTesting
    val movieList = mutableListOf<Movie>()

    /** Prevent local variables to be modified by different threads*/
    private val mutex = Mutex()

    override suspend fun getMovieList(isToForceRemote: Boolean): List<Movie> {
        if (isToForceRemote || movieList.isEmpty()) {
            val results = movieService.movieList().results
            results?.map { it.getMovieItem() }?.let {
                mutex.withLock {
                    movieList.addAll(it)
                }
            }
        }
        return mutex.withLock { movieList }
    }
}