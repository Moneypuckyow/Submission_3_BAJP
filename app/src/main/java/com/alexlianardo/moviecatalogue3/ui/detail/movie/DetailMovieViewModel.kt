package com.alexlianardo.moviecatalogue3.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alexlianardo.moviecatalogue3.data.MovieCatalogueRepository
import com.alexlianardo.moviecatalogue3.data.source.local.entity.MovieEntity
import com.alexlianardo.moviecatalogue3.vo.Resource

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var getMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mDetailMovieId ->
        movieCatalogueRepository.getMovieById(mDetailMovieId)
    }

    fun setFavorite() {
        val movieResource = getMovie.value
        if (movieResource != null) {
            val movieDetail = movieResource.data
            val newState = !movieDetail?.movieFavorited!!
            movieCatalogueRepository.setMovieFavorite(movieDetail, newState)
        }
    }
}