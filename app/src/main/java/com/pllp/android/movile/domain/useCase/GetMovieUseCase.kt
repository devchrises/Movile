package com.pllp.android.movile.domain.useCase

import com.pllp.android.movile.domain.model.Movie
import com.pllp.android.movile.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Result<List<Movie>> = movieRepository.getMovies()
}