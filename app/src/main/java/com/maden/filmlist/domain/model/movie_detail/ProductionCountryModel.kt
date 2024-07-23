package com.maden.filmlist.domain.model.movie_detail

import com.maden.filmlist.data.remote.model.movie_detail.ProductionCountryDto

data class ProductionCountryModel(
    val name: String
)

fun ProductionCountryDto.toModel() = ProductionCountryModel(
    name = name ?: ""
)