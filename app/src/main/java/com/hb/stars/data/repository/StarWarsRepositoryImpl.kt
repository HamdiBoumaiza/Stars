package com.hb.stars.data.repository

import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.data.commun.onFlowStarts
import com.hb.stars.data.datasource.remote.StarWarsDataSource
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.domain.models.SpecieModel
import com.hb.stars.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StarWarsRepositoryImpl(private val starWarsDataSource: StarWarsDataSource) :
        StarWarsRepository {
    override suspend fun searchCharacters(input: String): Flow<StarWarsResult<List<CharacterModel>>> {
        return flow {
            starWarsDataSource.searchCharacters(input).run {
                when (this) {
                    is StarWarsResult.Success -> {
                        data?.let { emit(StarWarsResult.Success(data.map { it.mapToDomainModel() })) }
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()
    }

    override suspend fun getPlanet(planetUrl: String): Flow<StarWarsResult<PlanetModel>> =
            flow {
                starWarsDataSource.getPlanet(planetUrl).run {
                    when (this) {
                        is StarWarsResult.Success -> {
                            data?.let { emit(StarWarsResult.Success(it.mapToDomainModel())) }
                        }
                        is StarWarsResult.Error -> {
                            emit(StarWarsResult.Error(exception))
                        }
                    }
                }
            }.onFlowStarts()

    override suspend fun getSpecies(specieUrl: List<String>): Flow<StarWarsResult<List<SpecieModel>>> =
            flow {
                starWarsDataSource.getSpecies(specieUrl).run {
                    when (this) {
                        is StarWarsResult.Success -> {
                            emit(StarWarsResult.Success(data.map { specie -> specie!!.mapToDomainModel() }))
                        }
                        is StarWarsResult.Error -> {
                            emit(StarWarsResult.Error(exception))
                        }
                    }
                }
            }.onFlowStarts()

    override suspend fun getMovies(movieUrl: List<String>): Flow<StarWarsResult<List<MovieModel>>> =
            flow {
                when (val result = starWarsDataSource.getMovies(movieUrl)) {
                    is StarWarsResult.Success -> {
                        emit(StarWarsResult.Success(result.data.map { movie -> movie!!.mapToDomainModel() }))
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(result.exception))
                    }
                }
            }.onFlowStarts()
}
