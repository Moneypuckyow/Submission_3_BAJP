package com.alexlianardo.moviecatalogue3.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alexlianardo.moviecatalogue3.data.MovieCatalogueRepository
import com.alexlianardo.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.alexlianardo.moviecatalogue3.vo.Resource

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = movieCatalogueRepository.getAllTvShows()
}