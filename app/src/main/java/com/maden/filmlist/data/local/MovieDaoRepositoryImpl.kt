package com.maden.filmlist.data.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.dao.MovieDao
import com.maden.filmlist.data.local.model.MovieDetailDaoModel

class MovieDaoRepositoryImpl(
    private val dao: MovieDao
) : MovieDaoRepository {

    override suspend fun insertMovieDetail(movie: MovieDetailDaoModel): DataResource<Unit> {
        //Insert movie
        val result = runCatching {
            return DataResource.Success(dao.insertMovieDetail(movie))
        }

        //Error handling
        return DataResource.Error(
            result.exceptionOrNull()?.localizedMessage ?: "Something went wrong!"
        )
    }

    override suspend fun getAllMovies(): DataResource<List<MovieDetailDaoModel>> {
        //Get all movies
        val result = runCatching {
           return  DataResource.Success(dao.getAllMovies())
        }

        //Error handling
        return DataResource.Error(
            result.exceptionOrNull()?.localizedMessage ?: "Something went wrong!"
        )
    }

    override suspend fun getMovieById(movieId: Double): DataResource<MovieDetailDaoModel?> {
        //Get movie by id
        val result = runCatching {
            return  DataResource.Success(dao.getMovieDetailById(movieId.toInt()))
        }

        //Error handling
        return DataResource.Error(
            result.exceptionOrNull()?.localizedMessage ?: "Something went wrong!"
        )
    }

    override suspend fun deleteMovieById(movieId: Double): DataResource<Unit> {
        //Delete movie
        val result = runCatching {
            return DataResource.Success(dao.deleteMovieById(movieId.toInt()))
        }

        //Error handling
        return DataResource.Error(
            result.exceptionOrNull()?.localizedMessage ?: "Something went wrong!"
        )
    }

}