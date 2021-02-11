package com.kotlin.submission2.ui.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.submission2.R
import com.kotlin.submission2.databinding.FragmentMoviesBinding
import com.kotlin.submission2.model.DataEntity
import com.kotlin.submission2.ui.detail.DetailActivity
import com.kotlin.submission2.ui.home.HomeAdapter
import com.kotlin.submission2.ui.home.HomeViewModel
import com.kotlin.submission2.utils.Constant.BUNDLE1
import com.kotlin.submission2.utils.Constant.BUNDLE2
import com.kotlin.submission2.utils.Constant.BUNDLE_MOVIES

class MoviesFragment : Fragment(), HomeAdapter.ItemCallback {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[HomeViewModel::class.java]
            val movies = viewModel.getListMovies()

            val moviesAdapter = HomeAdapter(this)
            moviesAdapter.setItemList(movies)

            with(binding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = moviesAdapter
            }
        }

    }

    override fun onItemClicked(data: DataEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(BUNDLE1, data.id)
                .putExtra(BUNDLE2, BUNDLE_MOVIES)
        )
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}