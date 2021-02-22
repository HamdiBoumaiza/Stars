package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject


open class GetSpecieUseCase @Inject constructor(
    private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(specieUrl: String) = starWarsRepository.getSpecies(specieUrl)
}