package com.alexlianardo.moviecatalogue3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alexlianardo.moviecatalogue3.data.MovieCatalogueRepository
import com.alexlianardo.moviecatalogue3.data.source.local.entity.MovieEntity
import com.alexlianardo.moviecatalogue3.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getMovieFavorite(): LiveData<PagedList<MovieEntity>> {
        return movieCatalogueRepository.getFavoriteMovies()
    }

    fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>> {
        return movieCatalogueRepository.getFavoriteTvShow()
    }

    fun setMovieFavorite(movieEntity: MovieEntity) {
        val newState = !movieEntity.movieFavorited
        movieCatalogueRepository.setMovieFavorite(movieEntity, newState)
    }

    fun setTvShowFavorite(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.tvFavorited
        movieCatalogueRepository.setTvShowFavorite(tvShowEntity, newState)
    }
}