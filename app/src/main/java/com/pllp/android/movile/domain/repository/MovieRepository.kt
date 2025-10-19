package com.pllp.android.movile.domain.repository

import com.pllp.android.movile.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): Result<List<Movie>>
}