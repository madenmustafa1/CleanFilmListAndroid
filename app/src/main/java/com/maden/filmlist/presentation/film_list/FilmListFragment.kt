package com.maden.filmlist.presentation.film_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maden.filmlist.R
import com.maden.filmlist.common.DataResource
import com.maden.filmlist.databinding.FragmentFilmListBinding
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel
import com.maden.filmlist.presentation.film_list.adapter.FilmListAdapter
import com.maden.filmlist.presentation.film_list.adapter.FilmListAdapterListener
import com.maden.filmlist.presentation.widgets.showAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilmListFragment @Inject constructor() : Fragment(R.layout.fragment_film_list),
    FilmListAdapterListener {

    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private val _viewModel: FilmListViewModel by viewModels()
    private val _adapter = FilmListAdapter(this)
    private var _isLinearLayout = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilmListBinding.bind(view)
        initViews()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        _adapter.clearAdapter()
        _viewModel.getPopularMovies(pageNumber = 1)
    }

    private fun initViews() {
        binding.recyclerView.adapter = _adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager

        binding.gridList.setOnClickListener { switchListLayout() }
        binding.searchButton.setOnClickListener { toggleSearchBar() }
        binding.searchRemoveButton.setOnClickListener { toggleSearchBar() }

        binding.searchBar.addTextChangedListener(_viewModel.searchTextListener())
    }

    private fun observeData() {
        _viewModel.movieResponse.observe(viewLifecycleOwner) {
            if (!isAdded) return@observe

            when (it) {
                is DataResource.Error -> {
                    requireContext().showAlertDialog(
                        title = getString(R.string.error_data),
                        message = getString(R.string.try_again)
                    ) {
                        _viewModel.getPopularMovies()
                    }
                }

                is DataResource.Loading -> {}
                is DataResource.Success -> {
                    _adapter.addItems(it.data!!.resultList)
                }
            }
        }

        _viewModel.filteredMovies.observe(viewLifecycleOwner) {
            if (!isAdded) return@observe
            _adapter.search(it!!)
        }
    }

    private fun switchListLayout() {
        _isLinearLayout = !_isLinearLayout
        if (_isLinearLayout) {
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.gridList.setImageResource(R.drawable.grid)
        } else {
            binding.gridList.setImageResource(R.drawable.list)
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        _adapter.notifyItemRangeChanged(0, _adapter.itemCount)
    }

    private fun toggleSearchBar() {
        if (binding.toolbar.visibility == View.GONE) {
            binding.toolbar.visibility = View.VISIBLE
            binding.toolbarSearch.visibility = View.GONE
        } else {
            binding.toolbar.visibility = View.GONE
            binding.toolbarSearch.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(item: MovieItemResponseModel) {
        val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailFragment(item)
        findNavController().navigate(action)
    }

    override fun lastItem(isLastItem: Boolean) {
        _viewModel.getPopularMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}