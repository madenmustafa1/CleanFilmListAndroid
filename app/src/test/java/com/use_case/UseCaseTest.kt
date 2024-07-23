package com.use_case

import com.maden.fake_repository.FakeApiRepository
import com.maden.filmlist.common.DataResource
import com.maden.filmlist.data.remote.model.movie_detail.MovieDetailResponseDto
import com.maden.filmlist.data.remote.model.movie_list.MovieResponseDto
import com.maden.filmlist.data.remote.service.movie.MovieApiService
import com.maden.filmlist.domain.use_cases.remote.GetMovieDetailsUseCase
import com.maden.filmlist.domain.use_cases.remote.GetPopularMoviesUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class MovieApiServiceTest {

    private lateinit var fakeApiRepository: FakeApiRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        fakeApiRepository = FakeApiRepository()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(fakeApiRepository)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(fakeApiRepository)
    }

    @Test
    fun `getPopularMovies returns expected data`(): Unit = runBlocking {
        // Given
        val pageNumber = 1

        // When
        val result = getPopularMoviesUseCase.execute(pageNumber)

        // Then
        assertTrue(result is DataResource.Success)
    }


    @Test
    fun `getMovieDetails returns expected data`(): Unit = runBlocking {
        // Given
        val movieId = 123

        // When
        val result = getMovieDetailsUseCase.execute(movieId)

        // Then
        assertTrue(result is DataResource.Success)
    }
}