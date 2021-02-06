package com.kotlin.submission1.ui.detail

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kotlin.submission1.R
import com.kotlin.submission1.databinding.ActivityDetailBinding
import com.kotlin.submission1.model.DataEntity
import com.kotlin.submission1.utils.Constant.BUNDLE1
import com.kotlin.submission1.utils.Constant.BUNDLE2
import com.kotlin.submission1.utils.Constant.BUNDLE_MOVIES
import com.kotlin.submission1.utils.Constant.BUNDLE_TVSERIES
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var data: DataEntity
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]


        val bundle1 = intent.getStringExtra(BUNDLE1)
        val bundle2 = intent.getStringExtra(BUNDLE2)

        if (bundle2.equals(BUNDLE_MOVIES)) {
            if (bundle1 != null) {
                viewModel.setMovies(bundle1)
            }
            data = viewModel.getMovies()
        } else if (bundle2.equals(BUNDLE_TVSERIES)) {
            if (bundle1 != null) {
                viewModel.setTvSeries(bundle1)
            }
            data = viewModel.getTvSeries()
        }

        initListener()
    }

    private fun initListener() {
        with(binding) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvGenre.text = data.genre
            tvYear.text = data.yearRelease
            tvRating.text = data.rating
            tvPopularity.text = data.popularity
            tvDirector.text = getString(R.string.director, data.directorOrCreator)
            tvStars.text = getString(R.string.stars, data.stars)
            tvReviews.text = data.reviews
            val metascore = data.metascore.toInt()
            tvMetascore.text = metascore.toString()

            circularRating.apply {
                setProgressWithAnimation(data.metascore, 2000)
                progressMax = 100f
                startAngle = 0f
                progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
            }

            ibBack.setOnClickListener {
                onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        setGlide(this, data.bgHeader, binding.ivHeader)
        setGlide(this, data.bgPoster, binding.ivPoster)

    }

    private fun setGlide(
        context: Context,
        loadImage: Int,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(loadImage)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .transform(RoundedCorners(20))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(imageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}