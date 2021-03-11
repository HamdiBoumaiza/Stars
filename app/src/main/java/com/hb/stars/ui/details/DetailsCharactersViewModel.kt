package com.hb.stars.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.domain.models.SpecieModel
import com.hb.stars.domain.usecases.GetMoviesUseCase
import com.hb.stars.domain.usecases.GetPlanetUseCase
import com.hb.stars.domain.usecases.GetSpeciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCharactersViewModel @Inject constructor(
        private val getMovieUseCase: GetMoviesUseCase,
        private val getPlanetUseCase: GetPlanetUseCase,
        private val getSpecieUseCase: GetSpeciesUseCase
) : ViewModel() {

    private val _resultMovie = MutableStateFlow<StarWarsResult<List<MovieModel>>>(StarWarsResult.Loading)
    val resultMovie: StateFlow<StarWarsResult<List<MovieModel>>> = _resultMovie

    fun getMovies(movieUrls: List<String>) {
        viewModelScope.launch {
            getMovieUseCase(movieUrls).collect {
                _resultMovie.emit(it)
            }
        }
    }

    private val _resultSpecie = MutableStateFlow<StarWarsResult<List<SpecieModel>>>(StarWarsResult.Loading)
    val resultSpecie: StateFlow<StarWarsResult<List<SpecieModel>>> = _resultSpecie

    fun getSpecies(movieUrls: List<String>) {
        viewModelScope.launch {
            getSpecieUseCase(movieUrls).collect {
                _resultSpecie.emit(it)
            }
        }
    }

    private val _resultPlanet = MutableStateFlow<StarWarsResult<PlanetModel>>(StarWarsResult.Loading)
    val resultPlanet: StateFlow<StarWarsResult<PlanetModel>> = _resultPlanet

    fun getPlanet(movieUrl: String) {
        viewModelScope.launch {
            getPlanetUseCase(movieUrl).collect {
                _resultPlanet.emit(it)
            }
        }
    }
}