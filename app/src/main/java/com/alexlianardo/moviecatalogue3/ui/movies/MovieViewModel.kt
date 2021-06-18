package com.alexlianardo.moviecatalogue3.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alexlianardo.moviecatalogue3.data.MovieCatalogueRepository
import com.alexlianardo.moviecatalogue3.data.source.local.entity.MovieEntity
import com.alexlianardo.moviecatalogue3.vo.Resource

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieCatalogueRepository.getAllMovies()
}