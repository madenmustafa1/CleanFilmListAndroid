package com.maden.filmlist.domain.model.movie_list

import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto

data class MovieResponseModel(
    val page: Int,
    var resultList: List<MovieItemResponseModel>,
    val totalPages: Int,
    val totalResults: Int
)

fun MovieResponseDto.toModel(): MovieResponseModel {
    return MovieResponseModel(
        page = page,
        resultList = results.map { it.toModel() },
        totalPages = total_pages,
        totalResults = total_results
    )
}
