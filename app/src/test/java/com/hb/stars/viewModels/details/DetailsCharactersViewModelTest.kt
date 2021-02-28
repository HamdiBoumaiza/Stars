package com.hb.stars.viewModels.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.hb.stars.data.commun.onError
import com.hb.stars.data.commun.onSuccess
import com.hb.stars.datasource.StarWarsDataSourceImplTest
import com.hb.stars.domain.usecases.GetMoviesUseCase
import com.hb.stars.domain.usecases.GetPlanetUseCase
import com.hb.stars.domain.usecases.GetSpeciesUseCase
import com.hb.stars.helpers.MainCoroutineRule
import com.hb.stars.ui.details.DetailsCharactersViewModel
import com.hb.stars.repository.StarWarsRepositoryImplTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailsCharactersViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailsCharactersViewModel

    @Before
    fun setup() {
        val repo = StarWarsRepositoryImplTest(StarWarsDataSourceImplTest())
        viewModel = DetailsCharactersViewModel(
            GetMoviesUseCase(repo),
            GetPlanetUseCase(repo),
            GetSpeciesUseCase(repo)
        )
    }

    @Test
    fun getSpecie() {
        viewModel.getSpecie("specie url")

        viewModel.resultSpecie.value?.onSuccess { result ->
            assertThat(result).isNotNull()
            assertThat(result.name).matches("Mirialan")
        }?.onError { error ->
            assertThat(error).isNull()
        }
    }

    @Test
    fun getMovie() {
        viewModel.getMovie("movie url")

        viewModel.resultMovie.value?.onSuccess { result ->
            assertThat(result).isNotNull()
            assertThat(result.title).matches("Attack of the Clones")
            assertThat(result.description).matches("There is unrest")
        }?.onError { error ->
            assertThat(error).isNull()
        }
    }

    @Test
    fun getPlanet() {
        viewModel.getPlanet("planet url")

        viewModel.resultPlanet.value?.onSuccess { result ->
            assertThat(result).isNotNull()
            assertThat(result.population).matches("200000")
        }?.onError { error ->
            assertThat(error).isNull()
        }
    }
}