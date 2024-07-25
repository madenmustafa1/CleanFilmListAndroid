package com.maden.filmlist.presentation.film_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maden.filmlist.common.DataResource
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import com.maden.filmlist.domain.use_cases.local.DeleteMovieByIdUseCase
import com.maden.filmlist.domain.use_cases.local.GetMovieByIdUseCase
import com.maden.filmlist.domain.use_cases.local.InsertMovieDetailUseCase
import com.maden.filmlist.domain.use_cases.remote.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val _getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val _insertMovieLocalUseCase: InsertMovieDetailUseCase,
    private val _getMovieByIdLocalUseCase: GetMovieByIdUseCase,
    private val _deleteMovieByIdLocalUseCase: DeleteMovieByIdUseCase
) : ViewModel() {

    private val _starFill = MutableLiveData<Boolean>()
    val starFill: LiveData<Boolean> = _starFill

    private val _movieDetail = MutableLiveData<DataResource<MovieDetailResponseModel>>()
    val movieDetail: LiveData<DataResource<MovieDetailResponseModel>> = _movieDetail

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO + _exceptionHandler) {
            //Check if movie is in database
            val checkMovie = _getMovieByIdLocalUseCase.execute(movieId = movieId.toDouble())

            if (checkMovie is DataResource.Success) {
                _movieDetail.postValue(checkMovie)
                return@launch
            }

            //Get movie detail
            val response = _getMovieDetailsUseCase.execute(movieId = movieId)

            //Check data
            if (response.data == null) {
                _movieDetail.postValue(DataResource.Error(message = "Something went wrong"))
                return@launch
            }

            _movieDetail.postValue(response)
        }
    }

    fun checkMovie(movie: MovieDetailResponseModel) {
        viewModelScope.launch(Dispatchers.IO + _exceptionHandler) {
            //Check if movie is in database
            val checkMovie = _getMovieByIdLocalUseCase.execute(movieId = movie.id ?: return@launch)

            //Check data
            if (checkMovie.data != null) _starFill.postValue(true)
            else _starFill.postValue(false)
        }
    }

    fun insertMovie(movie: MovieDetailResponseModel) {
        viewModelScope.launch(Dispatchers.IO + _exceptionHandler) {
            //Check if movie is in database
            val checkMovie =
                _getMovieByIdLocalUseCase.execute(movieId = movie.id ?: return@launch)

            //Check if movie is in database
            if (checkMovie.data == null) {
                //Insert movie
                _insertMovieLocalUseCase.execute(model = movie)
            } else {
                //Delete movie
                _deleteMovieByIdLocalUseCase.execute(movieId = movie.id)
            }

            checkMovie(movie)
        }
    }

    private val _exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetail.value = DataResource.Error(
            message = "An error occurred! ${exception.localizedMessage}"
        )
    }
}