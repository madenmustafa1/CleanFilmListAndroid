package com.maden.filmlist.domain.model.movie_detail

import com.maden.filmlist.data.remote.model.movie_detail.ProductionCompanyDto

data class ProductionCompanyModel(
    val logoPath: String,
    val name: String,
    val originCountry: String
)

fun ProductionCompanyDto.toModel() = ProductionCompanyModel(
    logoPath = logo_path ?: "",
    name = name ?: "",
    originCountry = origin_country ?: ""
)