package com.hb.stars.viewModels.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.hb.stars.data.commun.onError
import com.hb.stars.data.commun.onSuccess
import com.hb.stars.datasource.StarWarsDataSourceImplTest
import com.hb.stars.domain.usecases.SearchCharactersUseCase
import com.hb.stars.helpers.MainCoroutineRule
import com.hb.stars.helpers.runBlockingTest
import com.hb.stars.ui.search.SearchCharactersViewModel
import com.hb.stars.repository.StarWarsRepositoryImplTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchCharactersViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchCharactersViewModel

    @Before
    fun setup() {
        viewModel = SearchCharactersViewModel(
            SearchCharactersUseCase(
                StarWarsRepositoryImplTest(
                    StarWarsDataSourceImplTest()
                )
            )
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get list characters , returns success`() = mainCoroutineRule.runBlockingTest {
        viewModel.searchCharacters("lu")

        viewModel.resultListCharacters.value?.onSuccess { result ->
            assertThat(result).isNotNull()
            assertThat(result[0].name).isEqualTo("Luke Skywalker")
            assertThat(result.size).isAtLeast(2)
            assertThat(result[1].height).matches("170")
        }?.onError { error ->
            assertThat(error).isNull()
        }

    }
}