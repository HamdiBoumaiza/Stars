package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject

open class GetMoviesUseCase @Inject constructor(
        private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(movieUrls: List<String>) = starWarsRepository.getMovies(movieUrls)
}
