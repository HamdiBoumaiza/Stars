package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject

open class GetPlanetUseCase @Inject constructor(
        private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(planetUrl: String) = starWarsRepository.getPlanet(planetUrl)
}
