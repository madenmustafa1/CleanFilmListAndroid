package com.maden.filmlist.di

import android.app.Application
import androidx.room.Room
import com.maden.filmlist.BuildConfig.BASE_URL
import com.maden.filmlist.data.local.MovieDaoRepository
import com.maden.filmlist.data.local.MovieDaoRepositoryImpl
import com.maden.filmlist.data.local.dao.AppDatabase
import com.maden.filmlist.data.remote.service.movie.MovieApiRepository
import com.maden.filmlist.data.remote.service.movie.MovieApiService
import com.maden.filmlist.data.remote.service.movie.MovieApiServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    @Singleton
    @Provides
    fun injectRetrofitApi(): MovieApiServiceInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(MovieApiServiceInterface::class.java)
    }

    @Singleton
    @Provides
    fun injectRickAndMortyRepository(api: MovieApiServiceInterface): MovieApiService {
        return MovieApiRepository(_apiService = api)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: AppDatabase) : MovieDaoRepository {
        return MovieDaoRepositoryImpl(db.userDao())
    }

}