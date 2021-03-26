package com.kotlin.finalsub.ui.fragment.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.finalsub.R
import com.kotlin.finalsub.adapter.movies.ItemsMoviesCallback
import com.kotlin.finalsub.adapter.movies.MoviesFavoriteAdapter
import com.kotlin.finalsub.data.source.local.entities.MoviesEntity
import com.kotlin.finalsub.databinding.FragmentMoviesFavoriteBinding
import com.kotlin.finalsub.preference.SortPreference
import com.kotlin.finalsub.ui.DetailActivity
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE1
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE2
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_MOVIES
import com.kotlin.finalsub.utils.Constant.Companion.NEWEST
import com.kotlin.finalsub.utils.Constant.Companion.OLDEST
import com.kotlin.finalsub.utils.Constant.Companion.RATING
import com.kotlin.finalsub.utils.Constant.Companion.TITLE
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.viewmodel.ViewModelFactory

class MoviesFavoriteFragment : Fragment(), ItemsMoviesCallback {

    private var _binding: FragmentMoviesFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var moviesAdapter: MoviesFavoriteAdapter

    private lateinit var preference: SortPreference
    private var sorted = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = SortPreference()

        if (activity != null) {
            setupRecycler()
            getViewModel(sorted)
            loadSortItems()
            sortItems()
        }
    }

    override fun onResume() {
        super.onResume()

        getViewModel(sorted)
    }

    private fun getViewModel(sort: String) {
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getFavoriteMovies(sort).observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvMovies.hideShimmer()
                fetchData(it)
            }
        })
    }

    private fun fetchData(favoriteMoviesItem: PagedList<MoviesEntity>) {
        moviesAdapter.submitList(favoriteMoviesItem)
        when (favoriteMoviesItem.size) {
            0 -> noFavoriteMovies(true)
            else -> noFavoriteMovies(false)
        }
    }

    private fun setupRecycler() {
        moviesAdapter = MoviesFavoriteAdapter(requireContext(), this)
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun loadSortItems() {
        binding.apply {
            ratingChip.isChecked = preference.getSortMoviesByRating(requireContext())
            newestChip.isChecked = preference.getSortMoviesByNewest(requireContext())
            oldestChip.isChecked = preference.getSortMoviesByOldest(requireContext())
            titleChip.isChecked = preference.getSortMoviesByTitle(requireContext())
        }
        when {
            binding.ratingChip.isChecked -> sorted = RATING
            binding.newestChip.isChecked -> sorted = NEWEST
            binding.oldestChip.isChecked -> sorted = OLDEST
            binding.titleChip.isChecked -> sorted = TITLE
        }
        getViewModel(sorted)
    }

    private fun sortItems() {
        val chipId = binding.mainSortChip.checkedChipId
        if (chipId != -1) {
            binding.mainSortChip.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rating_chip -> {
                        sorted = RATING
                        preference.apply {
                            setSortMoviesByRating(requireContext())
                            clearSortMoviesByTitle(requireContext())
                            clearSortMoviesByNewest(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                    R.id.newest_chip -> {
                        sorted = NEWEST
                        preference.apply {
                            setSortMoviesByNewest(requireContext())
                            clearSortMoviesByTitle(requireContext())
                            clearSortMoviesByRating(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                    R.id.oldest_chip -> {
                        sorted = OLDEST
                        preference.apply {
                            setSortMoviesByOldest(requireContext())
                            clearSortMoviesByTitle(requireContext())
                            clearSortMoviesByRating(requireContext())
                            clearSortMoviesByNewest(requireContext())
                        }
                    }
                    R.id.title_chip -> {
                        sorted = TITLE
                        preference.apply {
                            setSortMoviesByTitle(requireContext())
                            clearSortMoviesByRating(requireContext())
                            clearSortMoviesByNewest(requireContext())
                            clearSortMoviesByOldest(requireContext())
                        }
                    }
                }
                getViewModel(sorted)
            }
        }
    }

    override fun onItemMoviesClicked(movies: MoviesEntity) {
        val intentToDetail = Intent(context, DetailActivity::class.java)
        intentToDetail.apply {
            putExtra(BUNDLE1, movies.id.toString())
            putExtra(BUNDLE2, BUNDLE_MOVIES)
        }
        context?.startActivity(intentToDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun noFavoriteMovies(state: Boolean) {
        binding.ivItemNotFound.isVisible = state
        binding.tvItemNotFound.isVisible = state
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}