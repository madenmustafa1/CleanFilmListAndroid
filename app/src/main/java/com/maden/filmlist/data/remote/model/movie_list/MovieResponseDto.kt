package com.maden.filmlist.data.remote.model.movie_list

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieItemResponseDto>,
    val total_pages: Int,
    val total_results: Int
)
