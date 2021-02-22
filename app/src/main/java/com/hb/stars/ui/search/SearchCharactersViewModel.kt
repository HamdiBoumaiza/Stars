package com.hb.stars.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.stars.data.commun.StarWarsResult
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.domain.usecases.SearchCharactersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchCharactersViewModel @Inject constructor(private val searchCharactersUseCase: SearchCharactersUseCase) :
    ViewModel() {

    private val _resultListCharacters = MutableLiveData<StarWarsResult<List<CharacterModel>>>()
    val resultListCharacters: LiveData<StarWarsResult<List<CharacterModel>>> = _resultListCharacters

    fun searchCharacters(input: String) {
        viewModelScope.launch {
            searchCharactersUseCase(input).collect {
                _resultListCharacters.postValue(it)
            }
        }
    }
}