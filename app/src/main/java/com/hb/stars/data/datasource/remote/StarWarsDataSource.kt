package com.hb.stars.data.datasource.remote

import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.response.CharacterResponse
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.data.response.PlanetResponse
import com.hb.stars.data.response.SpecieResponse

interface StarWarsDataSource {
    suspend fun searchCharacters(input: String): StarWarsResult<List<CharacterResponse>?>
    suspend fun getPlanet(planetUrl: String): StarWarsResult<PlanetResponse?>
    suspend fun getSpecies(specieUrls: List<String>): StarWarsResult<List<SpecieResponse?>>
    suspend fun getMovies(movieUrls: List<String>): StarWarsResult<ArrayList<MovieResponse?>>
}
