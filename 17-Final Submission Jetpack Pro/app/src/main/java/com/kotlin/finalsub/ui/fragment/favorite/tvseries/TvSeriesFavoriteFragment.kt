package com.kotlin.finalsub.ui.fragment.favorite.tvseries

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
import com.kotlin.finalsub.adapter.tvseries.ItemsTvSeriesCallback
import com.kotlin.finalsub.adapter.tvseries.TvSeriesFavoriteAdapter
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.databinding.FragmentTvSeriesFavoriteBinding
import com.kotlin.finalsub.preference.SortPreference
import com.kotlin.finalsub.ui.DetailActivity
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE1
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE2
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_TV_SERIES
import com.kotlin.finalsub.utils.Constant.Companion.NEWEST
import com.kotlin.finalsub.utils.Constant.Companion.OLDEST
import com.kotlin.finalsub.utils.Constant.Companion.RATING
import com.kotlin.finalsub.utils.Constant.Companion.TITLE
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.viewmodel.ViewModelFactory

class TvSeriesFavoriteFragment : Fragment(), ItemsTvSeriesCallback {

    private var _binding: FragmentTvSeriesFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var tvSeriesAdapter: TvSeriesFavoriteAdapter

    private lateinit var preference: SortPreference
    private var sorted = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvSeriesFavoriteBinding.inflate(layoutInflater, container, false)
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
        viewModel.getFavoriteTvSeries(sort).observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvMovies.hideShimmer()
                fetchData(it)
            }
        })
    }

    private fun fetchData(favoriteTvSeries: PagedList<TvSeriesEntity>) {
        tvSeriesAdapter.submitList(favoriteTvSeries)
        when (favoriteTvSeries.size) {
            0 -> noFavoriteTvSeries(true)
            else -> noFavoriteTvSeries(false)
        }
    }

    private fun setupRecycler() {
        tvSeriesAdapter = TvSeriesFavoriteAdapter(requireContext(), this)
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }
    }

    private fun loadSortItems() {
        binding.apply {
            ratingChip.isChecked = preference.getSortTvSeriesByRating(requireContext())
            newestChip.isChecked = preference.getSortTvSeriesByNewest(requireContext())
            oldestChip.isChecked = preference.getSortTvSeriesByOldest(requireContext())
            titleChip.isChecked = preference.getSortTvSeriesByTitle(requireContext())
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
                            setSortTvSeriesByRating(requireContext())
                            clearSortTvSeriesByTitle(requireContext())
                            clearSortTvSeriesByNewest(requireContext())
                            clearSortTvSeriesByOldest(requireContext())
                        }
                    }
                    R.id.newest_chip -> {
                        sorted = NEWEST
                        preference.apply {
                            setSortTvSeriesByNewest(requireContext())
                            clearSortTvSeriesByTitle(requireContext())
                            clearSortTvSeriesByRating(requireContext())
                            clearSortTvSeriesByOldest(requireContext())
                        }
                    }
                    R.id.oldest_chip -> {
                        sorted = OLDEST
                        preference.apply {
                            setSortTvSeriesByOldest(requireContext())
                            clearSortTvSeriesByTitle(requireContext())
                            clearSortTvSeriesByRating(requireContext())
                            clearSortTvSeriesByNewest(requireContext())
                        }
                    }
                    R.id.title_chip -> {
                        sorted = TITLE
                        preference.apply {
                            setSortTvSeriesByTitle(requireContext())
                            clearSortTvSeriesByRating(requireContext())
                            clearSortTvSeriesByNewest(requireContext())
                            clearSortTvSeriesByOldest(requireContext())
                        }
                    }
                }
                getViewModel(sorted)
            }
        }
    }

    override fun onItemTvSeriesClicked(tvSeries: TvSeriesEntity) {
        val intentToDetail = Intent(context, DetailActivity::class.java)
        intentToDetail.apply {
            putExtra(BUNDLE1, tvSeries.id.toString())
            putExtra(BUNDLE2, BUNDLE_TV_SERIES)
        }
        context?.startActivity(intentToDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun noFavoriteTvSeries(state: Boolean) {
        binding.apply {
            ivItemNotFound.isVisible = state
            tvItemNotFound.isVisible = state
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}