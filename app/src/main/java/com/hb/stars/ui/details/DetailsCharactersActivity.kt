package com.hb.stars.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.stars.R
import com.hb.stars.StarWarsApplication
import com.hb.stars.data.commun.DataSourceException
import com.hb.stars.data.commun.onError
import com.hb.stars.data.commun.onLoading
import com.hb.stars.data.commun.onSuccess
import com.hb.stars.databinding.ActivityDetailsCharactersBinding
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.utils.*
import okhttp3.ResponseBody
import javax.inject.Inject

class DetailsCharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { viewModelProvider(viewModelFactory) as DetailsCharactersViewModel }

    private lateinit var binding: ActivityDetailsCharactersBinding
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val speciesAdapter by lazy { SpeciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        StarWarsApplication.appComponent.inject(this)
        initObservers()
        initViews()
    }

    private fun initViews() {
        getExtraCharacter()?.apply {
            with(binding) {
                tvDetailsNameValue.text = name
                tvDetailsBirthYearValue.text = birthYear
                if (height.hasValue()) {
                    tvDetailsHeightValue.text =
                        getString(R.string.height_in_cm_and_feet, height, height.convertCmToFeet())
                } else {
                    tvDetailsHeightValue.text = UNDEFINED
                }
            }
            getPlanet(homeWorld)
            getMovies(this)
            getSpecies(this)
        }
    }

    private fun getPlanet(homeWorld: String) {
        if (homeWorld.hasValue()) viewModel.getPlanet(homeWorld.convertUrlToHttps())
    }

    private fun getSpecies(character: CharacterModel) {
        if (character.species.isNotEmpty()) {
            binding.cardSpecies.show()
            binding.rvSpecies.layoutManager =
                LinearLayoutManager(this@DetailsCharactersActivity)
            binding.rvSpecies.adapter = speciesAdapter
            character.species.forEach {
                viewModel.getSpecie(it.convertUrlToHttps())
            }
        }
    }

    private fun getMovies(character: CharacterModel) {
        if (character.films.isNotEmpty()) {
            binding.cardMovies.show()
            binding.rvMovies.layoutManager = LinearLayoutManager(this@DetailsCharactersActivity)
            binding.rvMovies.adapter = moviesAdapter
            character.films.forEach {
                viewModel.getMovie(it.convertUrlToHttps())
            }
        }
    }

    private fun getExtraCharacter() =
        intent?.extras?.getParcelable(CHARACTER_EXTRA) as CharacterModel?


    private fun initObservers() {
        viewModel.resultMovie.observe(this) {
            it.onSuccess { movie ->
                moviesAdapter.addItems(movie)
                binding.progressCircular.hide()
            }.onError { error ->
                showError(error)
                binding.progressCircular.show()
            }.onLoading {
                binding.progressCircular.show()
            }
        }

        viewModel.resultPlanet.observe(this) {
            it.onSuccess { planet ->
                binding.cardPopulation.show()
                binding.tvDetailsPopulation.text =
                    getString(
                        R.string.population_value,
                        getExtraCharacter()?.name,
                        planet.population
                    )
                binding.progressCircular.hide()
            }.onError { error ->
                showError(error)
                binding.progressCircular.show()
            }.onLoading {
                binding.progressCircular.show()
            }
        }

        viewModel.resultSpecie.observe(this) {
            it.onSuccess { specie ->
                speciesAdapter.addItems(specie)
                binding.progressCircular.hide()
            }.onError { error ->
                showError(error)
                binding.progressCircular.show()
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    private fun showError(error: DataSourceException) {
        when (error.messageResource) {
            is Int -> toast(getString(error.messageResource))
            is ResponseBody? -> toast(error.messageResource!!.string())
        }
    }
}