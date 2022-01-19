package com.spotlightapps.movietime.model


import com.google.gson.annotations.SerializedName

data class MovieApiModel(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var movieResults: List<MovieResult>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)

data class MovieResult(
    @SerializedName("adult")
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null
) {
    fun getMovieItem(): Movie {
        return Movie(
            id = id,
            title = title,
            posterPath = posterPath,
            voteAverage = voteAverage?.toString(),
            voteCount = voteCount?.toString()
        )
    }
}