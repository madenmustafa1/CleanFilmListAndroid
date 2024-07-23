package com.maden.filmlist.presentation.film_list

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maden.filmlist.common.DataResource
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel
import com.maden.filmlist.domain.model.movie_list.MovieResponseModel
import com.maden.filmlist.domain.use_cases.local.GetMovieListUseCase
import com.maden.filmlist.domain.use_cases.remote.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val _getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val _getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _movieResponse = MutableLiveData<DataResource<MovieResponseModel>>()
    val movieResponse: LiveData<DataResource<MovieResponseModel>> = _movieResponse

    private val _filteredMovies = MutableLiveData<List<MovieItemResponseModel>>()
    val filteredMovies: LiveData<List<MovieItemResponseModel>> = _filteredMovies

    private var _pageNumber = 1

    fun getPopularMovies(pageNumber: Int? = null) {
        viewModelScope.launch(Dispatchers.IO + _exceptionHandler) {
            val favoriteMovieListLocal = _getMovieListUseCase.execute().data

            _pageNumber = pageNumber ?: _pageNumber

            _movieResponse.postValue(DataResource.Loading())

            val response = _getPopularMoviesUseCase.execute(pageNumber = _pageNumber)

            if (response.data == null) {
                _movieResponse.postValue(DataResource.Error(message = "Something went wrong"))
                return@launch
            }

            val movieList = response.data.resultList.map {
                val checkFavorite = favoriteMovieListLocal?.find { movieLocal ->
                    (movieLocal.id?.toInt() ?: -1) == it.id
                }

                it.copy(isFavorite = checkFavorite != null)
            }

            _pageNumber++

            response.data.resultList = movieList
            _movieResponse.postValue(response)
            _filteredMovies.postValue(movieList)
        }
    }

    fun searchTextListener(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (it.length > 2) {
                        search(it.toString())
                    } else {
                        _filteredMovies.postValue(_movieResponse.value?.data?.resultList)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used
            }
        }
    }

    private fun search(searchText: String) {
        viewModelScope.launch(Dispatchers.IO + _exceptionHandler) {
            _movieResponse.value?.data?.resultList?.let { list ->
                val filteredList = list.filter { it.title.contains(searchText, ignoreCase = true) }
                _filteredMovies.postValue(filteredList)
            }
        }
    }


    private val _exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieResponse.value = DataResource.Error(
            message = "An error occurred! ${exception.localizedMessage}"
        )
    }
}