package com.kotlin.submission2.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.CastAdapter
import com.kotlin.submission2.R
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.databinding.ActivityDetailBinding
import com.kotlin.submission2.ui.home.HomeViewModel
import com.kotlin.submission2.utils.Constant.BUNDLE1
import com.kotlin.submission2.utils.Constant.BUNDLE2
import com.kotlin.submission2.utils.Constant.BUNDLE_MOVIES
import com.kotlin.submission2.utils.Constant.BUNDLE_TV_SERIES
import com.kotlin.submission2.utils.Constant.DATE_CURRENT_FORMAT
import com.kotlin.submission2.utils.Constant.DATE_REQUIRED_FORMAT
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.Constant.MAX_PROGRESS_CHART
import com.kotlin.submission2.utils.Constant.START_ANGLE_PROGRESS_CHART
import com.kotlin.submission2.utils.Helper.changeDateFormat
import com.kotlin.submission2.utils.Helper.joinGenres
import com.kotlin.submission2.utils.Helper.setGlideImages
import com.kotlin.submission2.viewmodel.ViewModelFactory
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var movies = listOf<MoviesCastItem>()
    private val castAdapter = CastAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle1 = intent.getStringExtra(BUNDLE1)
        val bundle2 = intent.getStringExtra(BUNDLE2)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        if (bundle2.equals(BUNDLE_MOVIES)) {
            if (bundle1 != null) {
                viewModel.getMoviesDetail(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, Observer {
                        hideLoading()
                        loadMovies(it)
                    })

                viewModel.getCast(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, Observer {
                        movies = it
                        castAdapter.setCast(movies)
                    })
            }
        } else if (bundle2.equals(BUNDLE_TV_SERIES)) {
            if (bundle1 != null) {
                viewModel.getTvSeriesDetail(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, Observer {
                        hideLoading()
                        loadTvSeries(it)
                    })
            }
        }
    }

    private fun loadMovies(movie: MoviesDetailItem) {
        val date = changeDateFormat(
            DATE_CURRENT_FORMAT,
            DATE_REQUIRED_FORMAT,
            movie.releaseDate
        )
        val genre = joinGenres(movie)

        with(binding) {
            tvTitle.text = movie.title
            tvDescription.text = movie.overview
            tvYear.text = date
            tvUserScore.text = movie.voteAverage.toString()
            tvRating.text = movie.voteAverage.toString()
            tvReviews.text = getString(R.string.reviews, movie.voteCount)
            tvGenre.text = genre.toString()
            tvRuntime.text = getString(R.string.runtime, movie.runtime)
            rvCast.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = castAdapter
            }

            circularRating.apply {
                setProgressWithAnimation(movie.voteAverage, 2000)
                progressMax = MAX_PROGRESS_CHART
                startAngle = START_ANGLE_PROGRESS_CHART
                progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
            }

            setGlideImages(
                this@DetailActivity,
                "$IMAGE_URL${movie.backdropPath}",
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                },
                binding.ivHeader
            )

            setGlideImages(
                this@DetailActivity,
                "$IMAGE_URL${movie.posterPath}",
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                },
                binding.ivPoster
            )

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

    private fun loadTvSeries(tvSeries: TvSeriesDetailItem) {
        val date = changeDateFormat(
            DATE_CURRENT_FORMAT,
            DATE_REQUIRED_FORMAT,
            tvSeries.firstAirDate
        )
        val genre = joinGenres(tvSeries)

        with(binding) {
            tvTitle.text = tvSeries.originalName
            tvDescription.text = tvSeries.overview
            tvYear.text = date
            tvUserScore.text = tvSeries.voteAverage.toString()
            tvRating.text = tvSeries.voteAverage.toString()
            tvReviews.text = getString(R.string.reviews, tvSeries.voteCount)
            tvGenre.text = genre.toString()
            tvRuntime.text = "-"

            circularRating.apply {
                setProgressWithAnimation(tvSeries.voteAverage, 2000)
                progressMax = MAX_PROGRESS_CHART
                startAngle = START_ANGLE_PROGRESS_CHART
                progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
            }

            setGlideImages(
                this@DetailActivity,
                "$IMAGE_URL${tvSeries.backdropPath}",
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                },
                binding.ivHeader
            )

            setGlideImages(
                this@DetailActivity,
                "$IMAGE_URL${tvSeries.posterPath}",
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                },
                binding.ivPoster
            )

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

    private fun hideLoading() {
        binding.group.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}