package com.maden.filmlist.data.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.model.MovieDetailDaoModel
import com.maden.filmlist.data.local.model.MovieItemDaoModel
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel


interface MovieDaoRepository {
    suspend fun insertMovieDetail(movie: MovieDetailDaoModel): DataResource<Unit>
    suspend fun getAllMovies(): DataResource<List<MovieDetailDaoModel>>
    suspend fun getMovieById(movieId: Double): DataResource<MovieDetailDaoModel?>
    suspend fun deleteMovieById(movieId: Double): DataResource<Unit>
}