package com.maden.filmlist.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maden.filmlist.domain.model.movie_detail.MovieDetailResponseModel

@Entity(tableName = "movieDetail")
data class MovieDetailDaoModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "adult") val adult: Boolean?,
    @ColumnInfo(name = "backdropPath") val backdropPath: String?,
    @ColumnInfo(name = "budget") val budget: Double?,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "imdbId") val imdbId: String?,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String?,
    @ColumnInfo(name = "originalTitle") val originalTitle: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "posterPath") val posterPath: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "revenue") val revenue: Double?,
    @ColumnInfo(name = "runtime") val runtime: Double?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "video") val video: Boolean?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "voteCount") val voteCount: Double?
)

fun MovieDetailResponseModel.toDao(): MovieDetailDaoModel {
    return MovieDetailDaoModel(
        id = id?.toInt() ?: throw Exception("id cannot be null"),
        title = title,
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}