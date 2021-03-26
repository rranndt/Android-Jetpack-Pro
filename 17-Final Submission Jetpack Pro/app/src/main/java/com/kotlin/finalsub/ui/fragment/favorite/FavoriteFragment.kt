package com.kotlin.finalsub.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.finalsub.R
import com.kotlin.finalsub.adapter.PagerAdapter
import com.kotlin.finalsub.databinding.FragmentFavoriteBinding
import com.kotlin.finalsub.ui.MainActivity
import com.kotlin.finalsub.ui.fragment.favorite.movies.MoviesFavoriteFragment
import com.kotlin.finalsub.ui.fragment.favorite.tvseries.TvSeriesFavoriteFragment

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setActionBarTitle(getString(R.string.title_favorites))

        val fragment = ArrayList<Fragment>()
        fragment.add(MoviesFavoriteFragment())
        fragment.add(TvSeriesFavoriteFragment())

        val titles = ArrayList<String>()
        titles.add(getString(R.string.movies))
        titles.add(getString(R.string.tv_series))

        val pagerAdapter = PagerAdapter(
            fragment,
            requireActivity()
        )

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}