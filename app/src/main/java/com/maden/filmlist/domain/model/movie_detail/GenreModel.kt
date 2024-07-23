package com.maden.filmlist.domain.model.movie_detail

import com.maden.filmlist.data.remote.model.movie_detail.GenreDto

data class GenreModel(
    val id: Double,
    val name: String
)

fun GenreDto.toModel() = GenreModel(
    id = id,
    name = name
)