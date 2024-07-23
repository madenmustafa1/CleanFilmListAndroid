package com.maden.filmlist.domain.use_cases.local

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.local.MovieDaoRepository
import com.maden.filmlist.data.local.model.toDao
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel
import javax.inject.Inject

class InsertMovieDetailUseCase @Inject constructor(
    private val _repository: MovieDaoRepository
) {

    /**
     * @param model MovieDetailResponseModel
     * @return DataResource<Unit>
     *
     * @throws Exception
     * @throws RuntimeException
     * @throws ClassNotFoundException
     */
    suspend fun execute(model: MovieDetailResponseModel): DataResource<Unit> {
        //Insert movie
        return _repository.insertMovieDetail(model.toDao())
    }

}