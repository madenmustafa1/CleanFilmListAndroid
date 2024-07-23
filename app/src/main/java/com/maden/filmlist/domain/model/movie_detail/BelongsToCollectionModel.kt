package com.maden.filmlist.domain.model.movie_detail

import com.maden.filmlist.data.remote.model.movie_detail.BelongsToCollectionDto

data class BelongsToCollectionModel(
    val backdropPath: String,
    val name: String,
    val posterPath: String
)


fun BelongsToCollectionDto.toModel() = BelongsToCollectionModel(
    backdropPath = backdrop_path ?: "",
    name = name ?: "",
    posterPath = poster_path ?: ""
)
