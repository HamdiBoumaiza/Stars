package com.hb.stars.data.datasource.remote

import com.hb.stars.data.commun.DataSourceException
import com.hb.stars.data.commun.RequestErrorHandler
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.response.CharacterResponse
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.data.response.PlanetResponse
import com.hb.stars.data.response.SpecieResponse
import kotlinx.coroutines.*

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

    override suspend fun getSpecies(specieUrl: String): StarWarsResult<SpecieResponse?> {
        return try {
            val e = withContext(Dispatchers.IO) {
                starWarsApi.getSpecies(specieUrl)
            }

            val result = starWarsApi.getSpecies(specieUrl)
            if (result.isSuccessful) {
                StarWarsResult.Success(result.body())
            } else {
                StarWarsResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getMovie(movieUrl: String): StarWarsResult<MovieResponse?> {
        return try {
            val result = starWarsApi.getMovie(movieUrl)
            if (result.isSuccessful) {
                StarWarsResult.Success(result.body())
            } else {
                StarWarsResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}

fun <T, V> CoroutineScope.asyncAll(list: List<T>, block: suspend (T) -> V): List<Deferred<V>> {
    return list.map {
        async { block.invoke(it) }
    }
}