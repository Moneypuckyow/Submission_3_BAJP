package com.alexlianardo.moviecatalogue3.ui.detail.tvShow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alexlianardo.moviecatalogue3.R
import com.alexlianardo.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.alexlianardo.moviecatalogue3.databinding.ActivityDetailBinding
import com.alexlianardo.moviecatalogue3.ui.viewmodel.ViewModelFactory
import com.alexlianardo.moviecatalogue3.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var activityDetailTvShowBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailTvShowViewModel

    private var menu: Menu? = null

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(activityDetailTvShowBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW, 0)
            viewModel.setSelectedTvShow(tvShowId)

            viewModel.getTvShow.observe(this, { tvShowResource ->
                if (tvShowResource != null) {
                    when(tvShowResource.status) {
                        Status.LOADING -> activityDetailTvShowBinding.progressBarDetail.visibility = View.VISIBLE
                        Status.SUCCESS -> if (tvShowResource.data != null) {
                            activityDetailTvShowBinding.progressBarDetail.visibility = View.GONE
                            populateTvShow(tvShowResource.data)
                        }
                        Status.ERROR -> {
                            activityDetailTvShowBinding.progressBarDetail.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        activityDetailTvShowBinding.txtTitle.text = tvShowEntity.tvTitle
        activityDetailTvShowBinding.genre.text = tvShowEntity.tvGenre
        activityDetailTvShowBinding.duration.text = tvShowEntity.tvSeason.toString()
        activityDetailTvShowBinding.txtDescription.text = tvShowEntity.tvDesc

        Glide.with(this)
            .load(tvShowEntity.tvPoster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imageView2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.getTvShow.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> activityDetailTvShowBinding.progressBarDetail.visibility = View.VISIBLE
                    Status.SUCCESS -> if (tvShow.data != null) {
                        activityDetailTvShowBinding.progressBarDetail.visibility = View.GONE
                        val state = tvShow.data.tvFavorited
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        activityDetailTvShowBinding.progressBarDetail.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_full_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }
}