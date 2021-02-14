package com.kotlin.submission2.data.repository.response.movies.detail

import com.google.gson.annotations.SerializedName
import com.kotlin.submission2.data.repository.response.movies.genres.MovieGenres

data class MoviesDetailResponse(

    @field:SerializedName("original_language")
	val originalLanguage: String,

    @field:SerializedName("title")
	val title: String,

    @field:SerializedName("backdrop_path")
	val backdropPath: String,

    @field:SerializedName("genres")
	val genres: List<MovieGenres>,

    @field:SerializedName("popularity")
	val popularity: Float,

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("vote_count")
	val voteCount: Int,

    @field:SerializedName("overview")
	val overview: String,

    @field:SerializedName("original_title")
	val originalTitle: String,

    @field:SerializedName("runtime")
	val runtime: Int,

    @field:SerializedName("poster_path")
	val posterPath: String,

    @field:SerializedName("release_date")
	val releaseDate: String,

    @field:SerializedName("vote_average")
	val voteAverage: Float,

    )