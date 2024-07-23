package com.maden.filmlist.domain.model.movie_detail

import com.maden.filmlist.data.remote.model.movie_detail.SpokenLanguageDto

data class SpokenLanguageModel(
    val englishName: String,
    val name: String
)

fun SpokenLanguageDto.toModel() = SpokenLanguageModel(
    englishName = english_name ?: "",
    name = name ?: ""
)