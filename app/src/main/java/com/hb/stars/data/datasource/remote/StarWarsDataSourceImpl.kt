package com.hb.stars.data.datasource.remote

import com.hb.stars.R
import com.hb.stars.data.commun.DataSourceException
import com.hb.stars.data.commun.RequestErrorHandler
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.commun.asyncAll
import com.hb.stars.data.response.CharacterResponse
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.data.response.PlanetResponse
import com.hb.stars.data.response.SpecieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class StarWarsDataSourceImpl(private val starWarsApi: StarWarsServices) : StarWarsDataSource {
    override suspend fun searchCharacters(input: String): StarWarsResult<List<CharacterResponse>?> {
        return try {
            val result = starWarsApi.searchCharacters(input)
            if (result.isSuccessful) {
                StarWarsResult.Success(result.body()?.results)
            } else {
                StarWarsResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getPlanet(planetUrl: String): StarWarsResult<PlanetResponse?> {
        return try {
            val result = starWarsApi.getPlanet(planetUrl)
            if (result.isSuccessful) {
                StarWarsResult.Success(result.body())
            } else {
                StarWarsResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getSpecies(specieUrls: List<String>): StarWarsResult<List<SpecieResponse?>> {
        return try {
            val species = ArrayList<SpecieResponse?>()
            withContext(Dispatchers.IO) {
                asyncAll(specieUrls) { starWarsApi.getSpecie(it) }
                        .awaitAll()
                        .forEach {
                            if (it.isSuccessful) {
                                species.add(it.body())
                            }
                        }
            }
            if (species.isNotEmpty()) {
                StarWarsResult.Success(species)
            } else {
                StarWarsResult.Error(DataSourceException.Server(R.string.error_unexpected_message))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getMovies(movieUrls: List<String>): StarWarsResult<ArrayList<MovieResponse?>> {
        return try {
            val movies = ArrayList<MovieResponse?>()
            withContext(Dispatchers.IO) {
                asyncAll(movieUrls) { starWarsApi.getMovie(it) }
                        .awaitAll()
                        .forEach {
                            if (it.isSuccessful) {
                                movies.add(it.body())
                            }
                        }
            }
            if (movies.isNotEmpty()) {
                StarWarsResult.Success(movies)
            } else {
                StarWarsResult.Error(DataSourceException.Server(R.string.error_unexpected_message))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}