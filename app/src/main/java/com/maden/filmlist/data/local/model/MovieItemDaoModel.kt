package com.maden.filmlist.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel

@Entity(tableName = "movie")
data class MovieItemDaoModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "voteCount") val voteCount: Int
)

fun MovieItemResponseModel.toDao(): MovieItemDaoModel {
    return MovieItemDaoModel(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteCount = voteCount
    )
}