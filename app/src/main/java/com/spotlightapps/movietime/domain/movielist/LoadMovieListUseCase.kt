package com.spotlightapps.movietime.domain.movielist

import com.spotlightapps.movietime.data.movie.MovieRepository
import com.spotlightapps.movietime.domain.FlowUseCase
import com.spotlightapps.movietime.model.Movie
import com.spotlightapps.movietime.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ahmad Jawid Muhammadi
 * on 19-01-2022.
 */

class LoadMovieListUseCase(
    private val movieRepository: MovieRepository,
    coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Boolean, List<Movie>>(coroutineDispatcher) {

    override fun execute(parameters: Boolean): Flow<Result<List<Movie>>> {
        return movieRepository.getMovieList(parameters).map {
            Result.Success(it)
        }
    }
}