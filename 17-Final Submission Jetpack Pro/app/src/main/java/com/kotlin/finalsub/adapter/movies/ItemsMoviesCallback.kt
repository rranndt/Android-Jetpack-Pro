package com.kotlin.finalsub.adapter.movies

import com.kotlin.finalsub.data.source.local.entities.MoviesEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface ItemsMoviesCallback {
    fun onItemMoviesClicked(movies: MoviesEntity)
}