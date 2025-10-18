package com.pllp.android.movile.domain.model

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genere: List<String>,
    val id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String
)