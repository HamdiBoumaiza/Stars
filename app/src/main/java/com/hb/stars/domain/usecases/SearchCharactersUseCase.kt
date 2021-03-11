package com.hb.stars.domain.usecases

import com.hb.stars.domain.repository.StarWarsRepository
import javax.inject.Inject

open class SearchCharactersUseCase @Inject constructor(
        private val starWarsRepository: StarWarsRepository
) {
    suspend operator fun invoke(input: String) = starWarsRepository.searchCharacters(input)
}
