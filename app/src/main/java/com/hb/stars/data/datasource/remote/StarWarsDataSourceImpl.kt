package com.hb.stars.data.datasource.remote

import com.hb.stars.data.commun.DataSourceException
import com.hb.stars.data.commun.RequestErrorHandler
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.response.CharacterResponse
import com.hb.stars.data.response.MovieResponse
import com.hb.stars.data.response.PlanetResponse
import com.hb.stars.data.response.SpecieResponse
import com.hb.stars.utils.convertUrlToHttps

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
            val movies = ArrayList<SpecieResponse?>()
            specieUrls.forEach {
                val result = starWarsApi.getSpecie(it)
                if (result.isSuccessful) {
                    movies.add(result.body()!!)
                }
            }
            if (movies.isNotEmpty()) {
                StarWarsResult.Success(movies)
            } else {
                StarWarsResult.Error(DataSourceException.Server(""))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getMovies(movieUrls: List<String>): StarWarsResult<ArrayList<MovieResponse?>> {
        return try {
            val movies = ArrayList<MovieResponse?>()
            movieUrls.forEach {
                val result = starWarsApi.getMovie(it)
                if (result.isSuccessful) {
                    movies.add(result.body()!!)
                }
            }
            if (movies.isNotEmpty()) {
                StarWarsResult.Success(movies)
            } else {
                StarWarsResult.Error(DataSourceException.Server(""))
            }
        } catch (e: Exception) {
            StarWarsResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}
