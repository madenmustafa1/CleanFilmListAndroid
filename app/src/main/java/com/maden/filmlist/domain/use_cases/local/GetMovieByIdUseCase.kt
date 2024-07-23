package com.maden.filmlist.domain.use_cases.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.MovieDaoRepository
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import com.maden.filmlist.domain.model.movie_detail.toModel
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel
import com.maden.filmlist.domain.model.movie_list.toModel
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val _repository: MovieDaoRepository
) {

    /**
     * @param movieId Movie id
     * @return MovieDetailResponseModel or Exception Message
     * @throws Exception
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    suspend fun execute(movieId: Double): DataResource<MovieDetailResponseModel> {
        //Get movie by id
        val response = _repository.getMovieById(movieId = movieId)

        //Check data
        response.data?.let {
            return DataResource.Success(it.toModel())
        }

        //Error handling
        return DataResource.Error(response.message ?: "Something went wrong")
    }
}