package com.spotlightapps.movietime.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spotlightapps.movietime.model.Movie
import com.spotlightapps.movietime.ui.movielist.MovieListAdapter

/**
 * Created by Ahmad Jawid Muhammadi
 * on 27-01-2022.
 */

@BindingAdapter(value = ["submitItems"])
fun RecyclerView.submitMovieItems(movieList: List<Movie>?) {
    if (movieList == null || movieList.isEmpty()) return
    if (this.adapter is MovieListAdapter) {
        (this.adapter as MovieListAdapter).submitList(movieList)
    }
}

@BindingAdapter(value = ["imageUrl"])
fun ImageView.setImageUrl(uri: String?) {
    Glide.with(this.context)
        .load(uri)
        .into(this)
}