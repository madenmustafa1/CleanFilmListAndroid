package com.maden.filmlist.presentation.film_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maden.filmlist.common.DataResource
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
        }
    }

    private val _exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieResponse.value = DataResource.Error(
            message = "An error occurred! ${exception.localizedMessage}"
        )
    }
}