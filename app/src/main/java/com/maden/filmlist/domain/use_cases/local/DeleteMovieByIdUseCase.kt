package com.maden.filmlist.domain.use_cases.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.MovieDaoRepository
import javax.inject.Inject

class DeleteMovieByIdUseCase @Inject constructor(
    private val _repository: MovieDaoRepository
) {

    /**
     * @param movieId Movie id
     * @return MovieDetailResponseModel or Exception Message
     * @throws Exception
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    suspend fun execute(movieId: Double): DataResource<Unit> {
        //Delete movie by id
        val response = _repository.deleteMovieById(movieId = movieId)

        //Check data
        response.data?.let {
            return DataResource.Success(Unit)
        }

        //Error handling
        return DataResource.Error(response.message ?: "Something went wrong")
    }
}