package com.maden.filmlist.domain.use_cases.remote

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.service.movie.MovieApiRepository
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import com.maden.filmlist.domain.model.movie_detail.toModel
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val _repository: MovieApiRepository
) {

    /**
     * @param movieId -> Get movie detail by movie id
     * @sample execute(movieId = 1)
     * @return MovieDetailResponseDto or Exception Message
     * @see DataResource
     *
     * @throws Exception
     * @throws RuntimeException
     * @throws TypeCastException
     * @throws HttpException
     */
    suspend fun execute(movieId: Int): DataResource<MovieDetailResponseModel> {
        //Get movie detail
        val response = _repository.getMovieDetails(movieId = movieId)

        //Check data
        if (response is DataResource.Error) {
            return DataResource.Error(
                message = response.message ?: "Unknown Error"
            )
        }

        //Check data
        if (response.data == null) {
            return DataResource.Error(
                message = response.message ?: "Response data null"
            )
        }

        //Return data
        return DataResource.Success(
            data = response.data.toModel()
        )
    }
}