package com.kotlin.finalsub.ui.fragment.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.finalsub.R
import com.kotlin.finalsub.adapter.movies.ItemsMoviesCallback
import com.kotlin.finalsub.adapter.movies.MoviesAdapter
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.databinding.FragmentMoviesBinding
import com.kotlin.finalsub.ui.DetailActivity
import com.kotlin.finalsub.ui.MainActivity
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE1
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE2
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_MOVIES
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.viewmodel.ViewModelFactory
import com.kotlin.finalsub.vo.Status.*

class MoviesFragment : Fragment(), ItemsMoviesCallback {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as MainActivity).setActionBarTitle(getString(R.string.title_movies))
            setupRecyclerView()
            fetchData()
        }

    }

    private fun fetchData() {
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.getMovies().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    LOADING -> showShimmerEffect()
                    SUCCESS -> {
                        hideShimmerEffect()
                        showMoviesItem(it.data)
                    }
                    ERROR -> {
                        showShimmerEffect()
                        Toast.makeText(
                            context,
                            getString(R.string.something_happen),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        })
    }

    private fun showMoviesItem(moviesItem: PagedList<MoviesEntity>?) {
        moviesAdapter.submitList(moviesItem)
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(requireContext(), this)
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    override fun onItemMoviesClicked(movies: MoviesEntity) {
        val intentMoviesDetail = Intent(context, DetailActivity::class.java)
        intentMoviesDetail.apply {
            putExtra(BUNDLE1, movies.id.toString())
            putExtra(BUNDLE2, BUNDLE_MOVIES)
        }
        context?.startActivity(intentMoviesDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun showShimmerEffect() {
        binding.rvMovies.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.rvMovies.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}