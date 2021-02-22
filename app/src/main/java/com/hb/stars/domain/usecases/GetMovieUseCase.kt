package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject


open class GetMovieUseCase @Inject constructor(
    private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(movieUrl: String) = starWarsRepository.getMovie(movieUrl)
}