package com.hb.stars.di.component

import com.hb.stars.di.module.NetworkModule
import com.hb.stars.di.module.RepositoriesModule
import com.hb.stars.di.module.ViewModelModule
import com.hb.stars.ui.details.DetailsCharactersActivity
import com.hb.stars.ui.search.SearchCharactersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [ViewModelModule::class, RepositoriesModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(searchCharactersActivity: SearchCharactersActivity)
    fun inject(detailsCharactersActivity: DetailsCharactersActivity)
}