package com.maden.filmlist.domain.use_cases.remote

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.service.movie.MovieApiRepository
import com.maden.filmlist.domain.model.movie_list.MovieResponseModel
import com.maden.filmlist.domain.model.movie_list.toModel
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val _repository: MovieApiRepository
) {

    /**
     * @param pageNumber -> Fetch the page according to the page number
     * @sample execute(pageNumber = 1)
     * @return MovieResponseModel or Exception Message
     * @see DataResource
     *
     * @throws Exception
     * @throws RuntimeException
     * @throws TypeCastException
     * @throws HttpException
     */
    suspend fun execute(pageNumber: Int): DataResource<MovieResponseModel> {
        val response = _repository.getPopularMovies(page = pageNumber)

        if (response is DataResource.Error) {
            return DataResource.Error(
                message = response.message ?: "Unknown Error"
            )
        }

        if (response.data == null) {
            return DataResource.Error(
                message = response.message ?: "Response data null"
            )
        }



        return DataResource.Success(
            data = response.data.toModel()
        )
    }
}
