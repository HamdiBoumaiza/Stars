package com.hb.stars.repository

import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.datasource.StarWarsDataSourceImplTest
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.domain.models.SpecieModel
import com.hb.stars.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class StarWarsRepositoryImplTest(private val starWarsDataSourceImplTest: StarWarsDataSourceImplTest) :
    StarWarsRepository {
    override suspend fun searchCharacters(input: String): Flow<StarWarsResult<List<CharacterModel>>> {
        return flow {
            starWarsDataSourceImplTest.searchCharacters(input).run {
                when (this) {
                    is StarWarsResult.Success -> {
                        data?.let { emit(StarWarsResult.Success(it.map { character -> character.mapToDomainModel() })) }
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(exception))
                    }
                }
            }
        }.onStart { emit(StarWarsResult.Loading) }
    }

    override suspend fun getPlanet(planetUrl: String): Flow<StarWarsResult<PlanetModel>> {
        return flow {
            starWarsDataSourceImplTest.getPlanet(planetUrl).run {
                when (this) {
                    is StarWarsResult.Success -> {
                        data?.let { emit(StarWarsResult.Success(it.mapToDomainModel())) }
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(exception))
                    }
                }
            }
        }.onStart { emit(StarWarsResult.Loading) }
    }

    override suspend fun getSpecies(specieUrl: String): Flow<StarWarsResult<SpecieModel>> {
        return flow {
            starWarsDataSourceImplTest.getSpecies(specieUrl).run {
                when (this) {
                    is StarWarsResult.Success -> {
                        data?.let { emit(StarWarsResult.Success(it.mapToDomainModel())) }
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(exception))
                    }
                }
            }
        }.onStart { emit(StarWarsResult.Loading) }
    }

    override suspend fun getMovie(movieUrl: String): Flow<StarWarsResult<MovieModel>> {
        return flow {
            starWarsDataSourceImplTest.getMovie(movieUrl).run {
                when (this) {
                    is StarWarsResult.Success -> {
                        data?.let { emit(StarWarsResult.Success(it.mapToDomainModel())) }
                    }
                    is StarWarsResult.Error -> {
                        emit(StarWarsResult.Error(exception))
                    }
                }
            }
        }.onStart { emit(StarWarsResult.Loading) }
    }

}