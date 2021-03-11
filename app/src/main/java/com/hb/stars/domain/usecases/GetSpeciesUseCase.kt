package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject

open class GetSpeciesUseCase @Inject constructor(
        private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(specieUrl: List<String>) = starWarsRepository.getSpecies(specieUrl)
}
