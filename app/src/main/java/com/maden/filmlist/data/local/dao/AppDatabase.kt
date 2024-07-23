package com.maden.filmlist.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maden.filmlist.data.local.model.MovieDetailDaoModel
import com.maden.filmlist.data.local.model.MovieItemDaoModel

@Database(entities = [MovieItemDaoModel::class, MovieDetailDaoModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_app"
    }
}