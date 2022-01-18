package com.spotlightapps.movietime.data.network

import com.spotlightapps.movietime.model.MovieApiModel
import retrofit2.http.GET

/**
 * Created by Ahmad Jawid Muhammadi
 * on 18-01-2022.
 */

interface MovieService {

    @GET(Endpoints.ENDPOINT_MOVIE)
    suspend fun movieList(): MovieApiModel
}