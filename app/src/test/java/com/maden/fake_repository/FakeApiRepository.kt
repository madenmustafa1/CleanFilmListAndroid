package com.maden.fake_repository

import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto
import com.maden.filmlist.data.remote.service.movie.MovieApiService

class FakeApiRepository : MovieApiService {

    override suspend fun getPopularMovies(page: Int): DataResource<MovieResponseDto> {
        if (page < 0) DataResource.Error<MovieResponseDto>("Invalid page number")
        return DataResource.Success(
            MovieResponseDto(
                results = listOf(),
                total_pages = 1,
                total_results = 0,
                page = 1
            )
        )
    }

    override suspend fun getMovieDetails(movieId: Int): DataResource<MovieDetailResponseDto> {
        if (movieId < 0) DataResource.Error<MovieDetailResponseDto>("Invalid movie ID")
        return DataResource.Success(
            MovieDetailResponseDto(
                id = 123.0,
                title = "Test Movie",
                overview = "Test Overview",
                poster_path = "poster_path",
                genreDtos = listOf(),
                popularity = 0.0,
                release_date = "release_date",
                vote_average = 0.0,
                vote_count = 0.0,
                runtime = 0.0,
                status = "status",
                tagline = "tagline",
                homepage = "homepage",
                budget = 0.0,
                revenue = 0.0,
                original_language = "original_language",
                original_title = "original_title",
                adult = false,
                video = false,
                backdrop_path = "backdrop_path",
                imdb_id = "imdb_id",
                production_companies = listOf(),
                production_countries = listOf(),
                spoken_languages = listOf(),
                belongs_to_collection = null,
                origin_country = listOf()
            )
        )
    }
}