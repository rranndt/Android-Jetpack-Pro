package com.kotlin.submission2.ui.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.submission2.R
import com.kotlin.submission2.data.repository.response.movies.list.MoviesListItem
import com.kotlin.submission2.databinding.FragmentMoviesBinding
import com.kotlin.submission2.ui.MainViewModel
import com.kotlin.submission2.ui.detail.DetailActivity
import com.kotlin.submission2.ui.home.movies.adapter.MoviesAdapter
import com.kotlin.submission2.utils.Constant.BUNDLE1
import com.kotlin.submission2.utils.Constant.BUNDLE2
import com.kotlin.submission2.utils.Constant.BUNDLE_MOVIES
import com.kotlin.submission2.utils.ExtensionFunctions.snackBar
import com.kotlin.submission2.utils.NetworkConnection
import com.kotlin.submission2.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), MoviesAdapter.ItemsMoviesCallback {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var movies = listOf<MoviesListItem>()

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

        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(requireActivity(), Observer {
            if (it) {
                getMovies()
                binding.includeNoInternet.placeholderNoInternet.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
            } else {
                binding.rvMovies.visibility = View.INVISIBLE
                binding.includeNoInternet.placeholderNoInternet.visibility = View.VISIBLE
                binding.fragmentMovies.snackBar(getString(R.string.no_internet))
            }
        })
    }

    private fun getMovies() {
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val moviesAdapter = MoviesAdapter(requireContext(), this)
            viewModel.movies.observe(viewLifecycleOwner, Observer {
                movies = it
                moviesAdapter.setItemList(movies)
            })

            with(binding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onItemMoviesClicked(movies: MoviesListItem) {
        val intentMoviesDetail = Intent(context, DetailActivity::class.java)
        intentMoviesDetail.apply {
            putExtra(BUNDLE1, movies.id.toString())
            putExtra(BUNDLE2, BUNDLE_MOVIES)
        }
        context?.startActivity(intentMoviesDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}