package com.maden.filmlist.data.remote.service.movie

import com.maden.filmlist.BuildConfig
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiServiceInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.ACCESS_KEY,
        @Query("page") page: Int
    ): Response<MovieResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = BuildConfig.ACCESS_KEY,
    ): Response<MovieDetailResponseDto>
}