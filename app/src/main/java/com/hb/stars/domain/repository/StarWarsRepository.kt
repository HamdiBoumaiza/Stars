package com.hb.stars.domain.repository

import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.domain.models.SpecieModel
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {

    suspend fun searchCharacters(input: String): Flow<StarWarsResult<List<CharacterModel>>>
    suspend fun getPlanet(planetUrl: String): Flow<StarWarsResult<PlanetModel>>
    suspend fun getSpecies(specieUrl: List<String>): Flow<StarWarsResult<List<SpecieModel>>>
    suspend fun getMovies(movieUrl: List<String>): Flow<StarWarsResult<List<MovieModel>>>
}
