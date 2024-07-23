package com.maden.filmlist.domain.model.movie_list

import android.os.Parcelable
import com.maden.filmlist.data.local.model.MovieItemDaoModel
import com.maden.filmlist.data.remote.model.movie_list.MovieItemResponseDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemResponseModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteCount: Int,
    var isFavorite: Boolean = false
) : Parcelable

fun MovieItemResponseDto.toModel(): MovieItemResponseModel {
    return MovieItemResponseModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w400$poster_path",
        voteCount = vote_count
    )
}

fun MovieItemDaoModel.toModel(): MovieItemResponseModel {
    return MovieItemResponseModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteCount = voteCount
    )
}