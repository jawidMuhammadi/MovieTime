package com.spotlightapps.movietime.domain.movielist

import com.spotlightapps.movietime.data.movie.MovieRepository
import com.spotlightapps.movietime.domain.CoroutineUseCase
import com.spotlightapps.movietime.model.Movie
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

class LoadMovieListUseCase(
    private val movieRepository: MovieRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<Boolean, List<Movie>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Boolean): List<Movie> {
        return movieRepository.getMovieList(parameters)
    }
}