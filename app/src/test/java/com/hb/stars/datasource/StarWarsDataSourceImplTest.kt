package com.hb.stars.datasource

import com.google.gson.Gson
import com.hb.stars.R
import com.hb.stars.data.commun.DataSourceException
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.datasource.remote.StarWarsDataSource
import com.hb.stars.data.response.*
import com.hb.stars.helpers.getJson
import com.hb.stars.utils.fromJsonToObjectType

class StarWarsDataSourceImplTest : StarWarsDataSource {

    override suspend fun searchCharacters(input: String): StarWarsResult<List<CharacterResponse>?> {
        val result =
            Gson().fromJsonToObjectType<ListCharacterResponse?>(getJson("list_characters.json"))
        return if (result != null) {
            StarWarsResult.Success(result.results)
        } else {
            StarWarsResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

    override suspend fun getPlanet(planetUrl: String): StarWarsResult<PlanetResponse?> {
        val result =
            Gson().fromJsonToObjectType<PlanetResponse?>(getJson("planet.json"))
        return if (result != null) {
            StarWarsResult.Success(result)
        } else {
            StarWarsResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

    override suspend fun getSpecies(specieUrl: String): StarWarsResult<SpecieResponse?> {
        val result =
            Gson().fromJsonToObjectType<SpecieResponse?>(getJson("specie.json"))
        return if (result != null) {
            StarWarsResult.Success(result)
        } else {
            StarWarsResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

    override suspend fun getMovie(movieUrl: String): StarWarsResult<MovieResponse?> {
        val result =
            Gson().fromJsonToObjectType<MovieResponse?>(getJson("movie.json"))
        return if (result != null) {
            StarWarsResult.Success(result)
        } else {
            StarWarsResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

}