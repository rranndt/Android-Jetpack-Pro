package com.kotlin.finalsub.ui.fragment.tvseries

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
import com.kotlin.finalsub.adapter.tvseries.ItemsTvSeriesCallback
import com.kotlin.finalsub.adapter.tvseries.TvSeriesAdapter
import com.kotlin.finalsub.data.source.local.entities.TvSeriesEntity
import com.kotlin.finalsub.databinding.FragmentTvSeriesBinding
import com.kotlin.finalsub.ui.DetailActivity
import com.kotlin.finalsub.ui.MainActivity
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE1
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE2
import com.kotlin.finalsub.utils.Constant.Companion.BUNDLE_TV_SERIES
import com.kotlin.finalsub.viewmodel.MainViewModel
import com.kotlin.finalsub.viewmodel.ViewModelFactory
import com.kotlin.finalsub.vo.Status.*

class TvSeriesFragment : Fragment(), ItemsTvSeriesCallback {

    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvSeriesAdapter: TvSeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            (activity as MainActivity).setActionBarTitle(getString(R.string.title_tv_series))
            setupRecyclerView()
            fetchData()
        }

    }

    private fun fetchData() {
        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.getTvSeries().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    LOADING -> showShimmerEffect()
                    SUCCESS -> {
                        hideShimmerEffect()
                        showTvSeriesItem(it.data)
                    }
                    ERROR -> {
                        showShimmerEffect()
                        Toast.makeText(
                            context,
                            getString(R.string.something_happen),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun showTvSeriesItem(tvSeriesItem: PagedList<TvSeriesEntity>?) {
        tvSeriesAdapter.submitList(tvSeriesItem)
    }

    private fun setupRecyclerView() {
        tvSeriesAdapter = TvSeriesAdapter(requireContext(), this)
        with(binding.rvTvSeries) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }
    }

    override fun onItemTvSeriesClicked(tvSeries: TvSeriesEntity) {
        val intentTvSeriesToDetail = Intent(context, DetailActivity::class.java)
        intentTvSeriesToDetail.apply {
            putExtra(BUNDLE1, tvSeries.id.toString())
            putExtra(BUNDLE2, BUNDLE_TV_SERIES)
        }
        context?.startActivity(intentTvSeriesToDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun showShimmerEffect() {
        binding.rvTvSeries.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.rvTvSeries.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}