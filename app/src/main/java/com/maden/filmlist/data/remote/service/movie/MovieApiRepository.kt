package com.maden.filmlist.data.remote.service.movie

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto
import javax.inject.Inject

class MovieApiRepository @Inject constructor(
    private val _apiService: MovieApiServiceInterface
) : MovieApiService {

    override suspend fun getPopularMovies(page: Int): DataResource<MovieResponseDto> {
        return try {
            //Request
            val response = _apiService.getPopularMovies(page = page)

            //Http Error handling
            if (!response.isSuccessful) {
                return DataResource.Error(
                    message = response.message().ifEmpty { "Something went wrong!" },
                )
            }

            //Response control
            if (response.body() == null) {
                return DataResource.Error(
                    message = "Something went wrong!",
                )
            }

            //Response
            DataResource.Success(data = response.body()!!)
        } catch (e: Exception) {
            //Error handling
            DataResource.Error(
                message = "Something went wrong!\n" + e.localizedMessage,
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): DataResource<MovieDetailResponseDto> {
        return try {
            //Request
            val response = _apiService.getMovieDetails(movieId = movieId)

            //Http Error handling
            if (!response.isSuccessful) {
                return DataResource.Error(
                    message = response.message().ifEmpty { "Something went wrong!" },
                )
            }

            //Response control
            if (response.body() == null) {
                return DataResource.Error(
                    message = "Something went wrong!",
                )
            }

            //Response
            DataResource.Success(data = response.body()!!)
        } catch (e: Exception) {
            //Error handling
            DataResource.Error(
                message = "Something went wrong!\n" + e.localizedMessage,
            )
        }
    }

}