package com.maden.filmlist.data.remote.model.movie_list

data class MovieItemResponseDto(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val vote_count: Int
)
