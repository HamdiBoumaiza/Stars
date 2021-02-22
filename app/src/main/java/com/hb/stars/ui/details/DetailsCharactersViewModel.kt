package com.hb.stars.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.domain.models.PlanetModel
import com.hb.stars.domain.models.SpecieModel
import com.hb.stars.domain.usecases.GetMovieUseCase
import com.hb.stars.domain.usecases.GetPlanetUseCase
import com.hb.stars.domain.usecases.GetSpecieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCharactersViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getSpecieUseCase: GetSpecieUseCase
) : ViewModel() {

    private var _resultMovie = MutableLiveData<StarWarsResult<MovieModel>>()
    var resultMovie: LiveData<StarWarsResult<MovieModel>> = _resultMovie

    fun getMovie(movieUrl: String) {
        viewModelScope.launch {
            getMovieUseCase(movieUrl).collect {
                _resultMovie.postValue(it)
            }
        }
    }

    private var _resultSpecie = MutableLiveData<StarWarsResult<SpecieModel>>()
    var resultSpecie: LiveData<StarWarsResult<SpecieModel>> = _resultSpecie

    fun getSpecie(movieUrl: String) {
        viewModelScope.launch {
            getSpecieUseCase(movieUrl).collect {
                _resultSpecie.postValue(it)
            }
        }
    }

    private var _resultPlanet = MutableLiveData<StarWarsResult<PlanetModel>>()
    var resultPlanet: LiveData<StarWarsResult<PlanetModel>> = _resultPlanet

    fun getPlanet(movieUrl: String) {
        viewModelScope.launch {
            getPlanetUseCase(movieUrl).collect {
                _resultPlanet.postValue(it)
            }
        }
    }
}