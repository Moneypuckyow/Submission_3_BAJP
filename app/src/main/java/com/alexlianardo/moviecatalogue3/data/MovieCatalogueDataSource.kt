package com.alexlianardo.moviecatalogue3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alexlianardo.moviecatalogue3.data.source.local.entity.MovieEntity
import com.alexlianardo.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.alexlianardo.moviecatalogue3.vo.Resource

interface MovieCatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}