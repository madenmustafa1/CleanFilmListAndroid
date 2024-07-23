package com.maden.filmlist.presentation.film_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maden.filmlist.R
import com.maden.filmlist.common.DataResource
import com.maden.filmlist.common.downloadImageWithUrl
import com.maden.filmlist.databinding.FragmentFilmDetailBinding
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilmDetailFragment @Inject constructor() : Fragment(R.layout.fragment_film_detail) {

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private val _args: FilmDetailFragmentArgs by navArgs()
    private val _viewModel: FilmDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilmDetailBinding.bind(view)
        observeData()
    }

    private fun observeData() {
        _viewModel.getMovieDetail(_args.movieModel.id)

        _viewModel.starFill.observe(viewLifecycleOwner) {
            if (!isAdded) return@observe

            if (it) binding.star.setImageResource(R.drawable.star_fill)
            else binding.star.setImageResource(R.drawable.star)
        }

        _viewModel.movieDetail.observe(viewLifecycleOwner) {
            if (!isAdded) return@observe

            when(it) {
                is DataResource.Error -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is DataResource.Loading -> {}
                is DataResource.Success -> {
                    initViews(it.data ?: return@observe)
                }
            }
        }
    }

    private fun initViews(model: MovieDetailResponseModel) {
        binding.poster.downloadImageWithUrl(_args.movieModel.posterPath)
        binding.title.text = model.title
        binding.content.text = model.overview
        binding.voteCount.text = model.voteCount.toString()
        _viewModel.checkMovie(model)

        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.star.setOnClickListener {
            _viewModel.insertMovie(movie = _viewModel.movieDetail.value?.data ?: return@setOnClickListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}