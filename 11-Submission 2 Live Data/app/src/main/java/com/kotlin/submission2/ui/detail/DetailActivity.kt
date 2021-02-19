package com.kotlin.submission2.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin.submission2.R
import com.kotlin.submission2.data.repository.response.movies.cast.MoviesCastItem
import com.kotlin.submission2.data.repository.response.movies.detail.MoviesDetailItem
import com.kotlin.submission2.data.repository.response.tv.cast.TvSeriesCastItem
import com.kotlin.submission2.data.repository.response.tv.detail.TvSeriesDetailItem
import com.kotlin.submission2.databinding.ActivityDetailBinding
import com.kotlin.submission2.ui.MainViewModel
import com.kotlin.submission2.ui.home.movies.adapter.MoviesCastAdapter
import com.kotlin.submission2.ui.home.tvseries.adapter.TvSeriesCastAdapter
import com.kotlin.submission2.utils.Constant.BUNDLE1
import com.kotlin.submission2.utils.Constant.BUNDLE2
import com.kotlin.submission2.utils.Constant.BUNDLE_MOVIES
import com.kotlin.submission2.utils.Constant.BUNDLE_TV_SERIES
import com.kotlin.submission2.utils.Constant.DATE_CURRENT_FORMAT
import com.kotlin.submission2.utils.Constant.DATE_REQUIRED_FORMAT
import com.kotlin.submission2.utils.Constant.IMAGE_URL
import com.kotlin.submission2.utils.Constant.MAX_PROGRESS_CHART
import com.kotlin.submission2.utils.Constant.START_ANGLE_PROGRESS_CHART
import com.kotlin.submission2.utils.ExtensionFunctions.gone
import com.kotlin.submission2.utils.ExtensionFunctions.show
import com.kotlin.submission2.utils.Helper.changeDateFormat
import com.kotlin.submission2.utils.Helper.joinGenres
import com.kotlin.submission2.utils.Helper.setGlideImages
import com.kotlin.submission2.viewmodel.ViewModelFactory
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.DecimalFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var moviesCast = listOf<MoviesCastItem>()
    private var tvSeriesCast = listOf<TvSeriesCastItem>()

    private val moviesCastAdapter = MoviesCastAdapter(this)
    private val tvSeriesCastAdapter = TvSeriesCastAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle1 = intent.getStringExtra(BUNDLE1)
        val bundle2 = intent.getStringExtra(BUNDLE2)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        if (bundle2.equals(BUNDLE_MOVIES)) {
            if (bundle1 != null) {
                // Main Movies Detail
                viewModel.getMoviesDetail(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, {
                        hideLoading()
                        loadMovies(it)
                    })

                // Movies Cast
                viewModel.getMoviesCast(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, {
                        moviesCast = it
                        moviesCastAdapter.setMoviesCast(moviesCast)
                    })
            }
        } else if (bundle2.equals(BUNDLE_TV_SERIES)) {
            if (bundle1 != null) {
                // Main Tv Series Detail
                viewModel.getTvSeriesDetail(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, {
                        hideLoading()
                        loadTvSeries(it)
                    })

                // Tv Series Cast
                viewModel.getTvSeriesCast(intent.getStringExtra(BUNDLE1)!!)
                    .observe(this, {
                        tvSeriesCast = it
                        tvSeriesCastAdapter.setTvSeriesCast(tvSeriesCast)
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
        val popularity = movie.popularity

        with(binding) {
            tvMainTitle.text = movie.title
            tvMainDescription.text = movie.overview
            tvMainYear.text = date
            tvMainUserScore.text = movie.voteAverage.toString()
            tvMainRating.text = movie.voteAverage.toString()
            tvMainPopularity.text = DecimalFormat("####.##").format(popularity)
            tvMainGenre.text = genre.toString()
            tvMainRuntime.text = getString(R.string.runtime, movie.runtime)
            rvCast.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = moviesCastAdapter
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
                binding.ivMainHeader
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
                binding.ivMainPoster
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
        val popularity = tvSeries.popularity

        with(binding) {
            tvMainTitle.text = tvSeries.originalName
            tvMainDescription.text = tvSeries.overview
            tvMainYear.text = date
            tvMainUserScore.text = tvSeries.voteAverage.toString()
            tvMainRating.text = tvSeries.voteAverage.toString()
            tvMainPopularity.text = DecimalFormat("####.##").format(popularity)
            tvMainGenre.text = genre.toString()
            tvMainRuntime.text = "-"
            rvCast.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = tvSeriesCastAdapter
            }

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
                binding.ivMainHeader
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
                binding.ivMainPoster
            )

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

    private fun hideLoading() {
        binding.group.show()
        binding.shimmerLayout.gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}