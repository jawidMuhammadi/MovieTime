package com.spotlightapps.movietime.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.spotlightapps.movietime.binding.submitMovieItems
import com.spotlightapps.movietime.databinding.FragmentMovieListBinding
import com.spotlightapps.movietime.util.launchAndRepeatWithViewLifecycle
import com.spotlightapps.movietime.util.showProgressBar
import com.spotlightapps.movietime.util.showToastMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var binding: FragmentMovieListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.rvMovieList.adapter = MovieListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchAndRepeatWithViewLifecycle {
            viewModel.uiState.collect {
                binding.progressBar.showProgressBar(it.isDataLoading)
                binding.rvMovieList.submitMovieItems(it.movieList)
                it.message?.let { message ->
                    showToastMessage(message)
                }
            }
        }
    }
}