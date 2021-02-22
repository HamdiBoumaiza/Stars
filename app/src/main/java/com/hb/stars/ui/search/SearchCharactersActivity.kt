package com.hb.stars.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.hb.stars.R
import com.hb.stars.StarWarsApplication
import com.hb.stars.data.commun.onError
import com.hb.stars.data.commun.onLoading
import com.hb.stars.data.commun.onSuccess
import com.hb.stars.databinding.ActivitySearchCharactersBinding
import com.hb.stars.di.viewmodel.DaggerViewModelFactory
import com.hb.stars.domain.models.CharacterModel
import com.hb.stars.ui.details.DetailsCharactersActivity
import com.hb.stars.utils.*
import okhttp3.ResponseBody
import javax.inject.Inject

class SearchCharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewModel by lazy { viewModelProvider(viewModelFactory) as SearchCharactersViewModel }

    private lateinit var binding: ActivitySearchCharactersBinding
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private lateinit var charactersAdapter: CharactersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        StarWarsApplication.appComponent.inject(this)
        checkInternetAvailability()
        setEditTextListener()
        initObserver()
        defaultSearch()
    }

    private fun defaultSearch() {
        viewModel.searchCharacters("")
    }

    private fun checkInternetAvailability() {
        ConnectionLiveData(this).observe(this) {
            if (!it) toast(getString(R.string.error_network))
        }
    }


    private fun initObserver() {
        viewModel.resultListCharacters.observe(this) {
            it.onSuccess { list ->
                setListAdapter(list)
            }.onError { error ->
                when (error.messageResource){
                    is Int -> setError(getString(error.messageResource))
                    is ResponseBody? -> setError(error.messageResource?.string())
                }
            }.onLoading {
                binding.groupError.hide()
                binding.progressCircular.show()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setEditTextListener() {
        binding.etSearch.addTextChangedListener {
            viewModel.searchCharacters(it.toString())
        }
        binding.etSearch.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    if (event.rawX >= binding.etSearch.right - binding.etSearch.compoundDrawables[2].bounds.width()) {
                        binding.etSearch.text = null
                    }
                }
                else -> view.performClick()
            }
            false
        }
    }

    private fun setListAdapter(list: List<CharacterModel>) {
        binding.progressCircular.hide()
        if (list.isEmpty()) {
            binding.groupError.show()
            binding.rvCharacters.hide()
        } else {
            binding.rvCharacters.show()
            binding.groupError.hide()
            binding.rvCharacters.show()
            if (!::charactersAdapter.isInitialized) {
                with(binding.rvCharacters) {
                    layoutManager = linearLayoutManager
                    charactersAdapter = CharactersAdapter { setOnCharacterClicked(it) }
                    adapter = charactersAdapter
                    charactersAdapter.submitList(list)
                }
            } else {
                charactersAdapter.submitList(list)
            }
        }
    }

    private fun setOnCharacterClicked(characterModel: CharacterModel) {
        Intent(this, DetailsCharactersActivity::class.java).apply {
            putExtra(CHARACTER_EXTRA, characterModel)
            startActivity(this)
        }
    }

    private fun setError(error: String?) {
        binding.rvCharacters.hide()
        binding.progressCircular.hide()
        binding.groupError.show()
        binding.tvError.text = error
    }
}