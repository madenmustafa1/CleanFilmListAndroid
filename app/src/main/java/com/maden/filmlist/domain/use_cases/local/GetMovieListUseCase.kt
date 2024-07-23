package com.maden.filmlist.domain.use_cases.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.MovieDaoRepository
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import com.maden.filmlist.domain.model.movie_detail.toModel
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel
import com.maden.filmlist.domain.model.movie_list.toModel
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val _repository: MovieDaoRepository
) {

    /**
     * @return List<MovieDetailResponseModel> or Exception Message
     * @throws Exception
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    suspend fun execute(): DataResource<List<MovieDetailResponseModel>> {
        //Get all movies
        val response = _repository.getAllMovies()

        //Check data
        response.data?.let {
            val newList = it.map { dto -> dto.toModel() }
            return DataResource.Success(newList)
        }

        //Error handling
        return DataResource.Error(response.message ?: "Something went wrong")
    }
}