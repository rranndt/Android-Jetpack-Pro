package com.kotlin.submission2.ui.home.tvseries

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
import com.kotlin.submission2.data.repository.response.tv.list.TvSeriesListItem
import com.kotlin.submission2.databinding.FragmentTvSeriesBinding
import com.kotlin.submission2.ui.MainViewModel
import com.kotlin.submission2.ui.detail.DetailActivity
import com.kotlin.submission2.ui.home.tvseries.adapter.TvSeriesAdapter
import com.kotlin.submission2.utils.Constant.BUNDLE1
import com.kotlin.submission2.utils.Constant.BUNDLE2
import com.kotlin.submission2.utils.Constant.BUNDLE_TV_SERIES
import com.kotlin.submission2.viewmodel.ViewModelFactory

class TvSeriesFragment : Fragment(), TvSeriesAdapter.ItemsTvSeriesCallback {

    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    private var tvSeries = listOf<TvSeriesListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

            val tvSeriesAdapter = TvSeriesAdapter(requireContext(), this)
            viewModel.tvSeries.observe(viewLifecycleOwner, Observer {
                tvSeries = it
                tvSeriesAdapter.setItemList(tvSeries)
            })

            with(binding.rvTvseries) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvSeriesAdapter
            }
        }
    }

    override fun onItemTvSeriesClicked(tvSeries: TvSeriesListItem) {
        val intentTvSeriesToDetail = Intent(context, DetailActivity::class.java)
        intentTvSeriesToDetail.apply {
            putExtra(BUNDLE1, tvSeries.id.toString())
            putExtra(BUNDLE2, BUNDLE_TV_SERIES)
        }
        context?.startActivity(intentTvSeriesToDetail)
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}