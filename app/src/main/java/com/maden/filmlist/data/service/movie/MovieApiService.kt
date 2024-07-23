package com.maden.filmlist.data.service.movie

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto

interface MovieApiService {
    suspend fun getPopularMovies(page: Int): DataResource<MovieResponseDto>
    suspend fun getMovieDetails(movieId: Int): DataResource<MovieDetailResponseDto>
}