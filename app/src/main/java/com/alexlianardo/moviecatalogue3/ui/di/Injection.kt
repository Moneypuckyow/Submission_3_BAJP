package com.alexlianardo.moviecatalogue3.ui.di

import android.content.Context
import com.alexlianardo.moviecatalogue3.data.MovieCatalogueRepository
import com.alexlianardo.moviecatalogue3.data.source.local.LocalDataSource
import com.alexlianardo.moviecatalogue3.data.source.local.room.MovieCatalogueDatabase
import com.alexlianardo.moviecatalogue3.data.source.remote.RemoteDataSource
import com.alexlianardo.moviecatalogue3.utils.AppExecutors
import com.alexlianardo.moviecatalogue3.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val database = MovieCatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieCatalogueDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}