package com.hb.stars.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hb.stars.di.viewmodel.DaggerViewModelFactory
import com.hb.stars.di.viewmodel.ViewModelKey
import com.hb.stars.ui.details.DetailsCharactersViewModel
import com.hb.stars.ui.search.SearchCharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchCharactersViewModel::class)
    abstract fun bindSearchVM(searchCharactersViewModel: SearchCharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsCharactersViewModel::class)
    abstract fun bindDetailsVM(detailsCharactersViewModel: DetailsCharactersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
