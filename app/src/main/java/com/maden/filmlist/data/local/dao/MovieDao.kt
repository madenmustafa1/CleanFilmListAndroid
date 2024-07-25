package com.maden.filmlist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maden.filmlist.data.local.model.MovieDetailDaoModel

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: MovieDetailDaoModel)

    @Query("SELECT * FROM movieDetail WHERE id = :movieId")
    fun getMovieDetailById(movieId: Int): MovieDetailDaoModel?

    @Query("SELECT * FROM movieDetail")
    fun getAllMovies(): List<MovieDetailDaoModel>

    @Query("DELETE FROM movieDetail WHERE id = :movieId")
    fun deleteMovieById(movieId: Int)
}