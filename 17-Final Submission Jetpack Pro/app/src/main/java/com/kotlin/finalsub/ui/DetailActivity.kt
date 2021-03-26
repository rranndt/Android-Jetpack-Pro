package com.kotlin.finalsub.ui

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kotlin.final.utils.ExtensionFunctions.gone
import com.kotlin.final.utils.ExtensionFunctions.show
import com.kotlin.finalsub.BuildConfig.IMAGE_URL
import com.kotlin.finalsub.R
import com.kotlin.finalsub.adapter.movies.MoviesCastAdapter
import com.kotlin.finalsub.adapter.tvseries.TvSeriesCastAdapter
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.data.source.remote.response.movies.cast.MoviesCastItem
import com.kotlin.finalsub.data.source.remote.response.tv.cast.TvSeriesCastItem
import com.kotlin.finalsub.databinding.ActivityDetailBinding
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE1
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE2
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_MOVIES
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_TV_SERIES
import com.kotlin.finalsub.utils.Constant.Companion.DATE_CURRENT_FORMAT
import com.kotlin.finalsub.utils.Constant.Companion.DATE_REQUIRED_FORMAT
import com.kotlin.finalsub.utils.Constant.Companion.MAX_PROGRESS_CHART
import com.kotlin.finalsub.utils.Constant.Companion.START_ANGLE_PROGRESS_CHART
import com.kotlin.finalsub.utils.Helper.changeDateFormat
import com.kotlin.finalsub.utils.Helper.setImageView
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.viewmodel.ViewModelFactory
import com.kotlin.finalsub.vo.Status.*
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.DecimalFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var moviesCast = listOf<MoviesCastItem>()
    private var tvSeriesCast = listOf<TvSeriesCastItem>()

    private val moviesCastAdapter = MoviesCastAdapter(this)
    private val tvSeriesCastAdapter = TvSeriesCastAdapter(this)

    private lateinit var viewModel: MainViewModel
    private var category: String? = null

    private var newState: Boolean = false
    private var moviesId: String = ""
    private var moviesTitle: String = ""
    private var moviesYear: String = ""
    private var moviesOverview: String = ""
    private var tvSeriesId: String = ""
    private var tvSeriesTitle: String = ""
    private var tvSeriesYear: String = ""
    private var tvSeriesOverview: String = ""

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_bottom_anim
        )
    }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(BUNDLE1)
            category = extras.getString(BUNDLE2)

            if (id != null && category != null) {
                viewModel.setCatalogue(id, category.toString())
                if (category == BUNDLE_MOVIES) {
                    viewModel.getDetailMovies().observe(this, {
                        when (it.status) {
                            LOADING -> {
                                showShimmerEffect()
                            }
                            SUCCESS -> {
                                if (it.data != null) {
                                    hideShimmerEffect()
                                    hideLoading()
                                    loadMovies(it.data)

                                    // Set Favorites
                                    val favorite = it.data.isFavorite
                                    stateFavorite(favorite)
                                }
                            }
                            ERROR -> {
                                showShimmerEffect()
                                customToast(getString(R.string.something_happen))
                            }
                        }
                    })
                } else if (category == BUNDLE_TV_SERIES) {
                    viewModel.getDetailTvSeries().observe(this, {
                        when (it.status) {
                            LOADING -> {
                                showShimmerEffect()
                            }
                            SUCCESS -> {
                                if (it.data != null) {
                                    hideShimmerEffect()
                                    hideLoading()
                                    loadTvSeries(it.data)

                                    // Set Favorites
                                    val favorite = it.data.isFavorite
                                    stateFavorite(favorite)
                                }
                            }
                            ERROR -> {
                                showShimmerEffect()
                                customToast(getString(R.string.something_happen))
                            }
                        }
                    })
                }
            }
        }

        onFloatingMenuClick()
    }

    /**
     * Load Items
     */
    private fun loadMovies(movie: MoviesEntity) {
        val date = changeDateFormat(
            DATE_CURRENT_FORMAT,
            DATE_REQUIRED_FORMAT,
            movie.releaseDate
        )
        val genre = getString(R.string.genre, movie.genres)
        val popularity = movie.popularity

        with(binding) {
            moviesId = movie.id.toString()
            moviesTitle = movie.title
            moviesYear = date
            moviesOverview = movie.overview

            tvMainTitle.text = moviesTitle
            tvMainDescription.text = moviesOverview
            tvMainYear.text = moviesYear

            if (movie.voteAverage == 0.0F) {
                tvMainUserScore.text = getString(R.string.no_rating)
                tvMainRating.text = getString(R.string.no_rating)
            } else {
                tvMainUserScore.text = movie.voteAverage.toString()
                tvMainRating.text = movie.voteAverage.toString()
            }
            tvMainPopularity.text = DecimalFormat("####.##").format(popularity)
            tvMainGenre.text = genre
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

            setImageView(
                binding.ivMainHeader,
                "${IMAGE_URL}${movie.backdropPath}"
            )
            setImageView(
                binding.ivMainPoster,
                "${IMAGE_URL}${movie.posterPath}"
            )

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        viewModel.getMoviesCast(intent.getStringExtra(BUNDLE1)!!)
            .observe(this, {
                moviesCast = it
                moviesCastAdapter.setMoviesCast(moviesCast)
            })
    }

    private fun loadTvSeries(tvSeries: TvSeriesEntity) {
        val date = changeDateFormat(
            DATE_CURRENT_FORMAT,
            DATE_REQUIRED_FORMAT,
            tvSeries.releaseData
        )
        val genre = getString(R.string.genre, tvSeries.genres)
        val popularity = tvSeries.popularity

        with(binding) {
            tvSeriesId = tvSeries.id.toString()
            tvSeriesTitle = tvSeries.title
            tvSeriesYear = date
            tvSeriesOverview = tvSeries.overview

            tvMainTitle.text = tvSeriesTitle
            tvMainDescription.text = tvSeriesOverview
            tvMainYear.text = tvSeriesYear
            tvMainUserScore.text = tvSeries.voteAverage.toString()
            tvMainRating.text = tvSeries.voteAverage.toString()
            tvMainPopularity.text = DecimalFormat("####.##").format(popularity)
            tvMainGenre.text = genre
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

            setImageView(
                binding.ivMainHeader,
                "${IMAGE_URL}${tvSeries.backdropPath}"
            )
            setImageView(
                binding.ivMainPoster,
                "${IMAGE_URL}${tvSeries.posterPath}"
            )

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        viewModel.getTvSeriesCast(intent.getStringExtra(BUNDLE1)!!)
            .observe(this, {
                tvSeriesCast = it
                tvSeriesCastAdapter.setTvSeriesCast(tvSeriesCast)
            })
    }

    /**
     * Floating Menu
     */
    private fun onClickFab() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.floatingFavorite.isVisible = true
            binding.floatingShare.isVisible = true
        } else {
            binding.floatingFavorite.isVisible = false
            binding.floatingShare.isVisible = false
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.floatingFavorite.startAnimation(fromBottom)
            binding.floatingShare.startAnimation(fromBottom)
            binding.floatingMenu.startAnimation(rotateOpen)
        } else {
            binding.floatingFavorite.startAnimation(toBottom)
            binding.floatingShare.startAnimation(toBottom)
            binding.floatingMenu.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            binding.floatingFavorite.isClickable = true
            binding.floatingShare.isClickable = true
        } else {
            binding.floatingFavorite.isClickable = false
            binding.floatingShare.isClickable = false
        }
    }

    private fun onFloatingMenuClick() {
        binding.floatingFavorite.setOnClickListener {
            if (category == BUNDLE_MOVIES) {
                viewModel.insertMovies()
                if (newState) {
                    showSnackBar(getString(R.string.remove_from_favorite, moviesTitle))
                } else {
                    showSnackBar(getString(R.string.add_to_favorite, moviesTitle))
                }
            } else if (category == BUNDLE_TV_SERIES) {
                viewModel.insertTvSeries()
                if (newState) {
                    showSnackBar(getString(R.string.remove_from_favorite, tvSeriesTitle))
                } else {
                    showSnackBar(getString(R.string.add_to_favorite, tvSeriesTitle))
                }
            }
        }

        binding.floatingMenu.setOnClickListener {
            onClickFab()
        }

        binding.floatingShare.setOnClickListener {
            onMoviesShare()
        }
    }

    private fun stateFavorite(state: Boolean): Boolean {
        val favorite = binding.floatingFavorite
        newState = if (state) {
            favorite.setImageResource(R.drawable.ic_favorite)
            true
        } else {
            favorite.setImageResource(R.drawable.ic_favorite_border)
            false
        }
        return state
    }

    private fun onMoviesShare() {
        var shareMovies = ""

        if (category == BUNDLE_MOVIES) {
            shareMovies =
                "${getString(R.string.share_title, moviesTitle)} \n" +
                        "${getString(R.string.share_year, moviesYear)} \n" +
                        "${getString(R.string.share_overview, moviesOverview)} \n" +
                        getString(R.string.share_link, moviesId)
        } else if (category == BUNDLE_TV_SERIES) {
            shareMovies =
                "${getString(R.string.share_title, tvSeriesTitle)} \n" +
                        "${getString(R.string.share_year, tvSeriesYear)} \n" +
                        "${getString(R.string.share_overview, tvSeriesOverview)} \n" +
                        getString(R.string.share_link, tvSeriesId)
        }

        val type = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(type)
            .setText(shareMovies)
            .startChooser()
    }

    private fun customToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.detailLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun hideLoading() {
        binding.group.show()
        binding.shimmerLayout.gone()
    }

    private fun showShimmerEffect() {
        binding.shimmerLayout.isVisible = true
    }

    private fun hideShimmerEffect() {
        binding.shimmerLayout.isVisible = false
    }

}