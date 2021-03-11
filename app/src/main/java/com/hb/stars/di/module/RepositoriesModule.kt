package com.hb.stars.di.module

import com.hb.stars.data.datasource.remote.StarWarsDataSourceImpl
import com.hb.stars.data.datasource.remote.StarWarsServices
import com.hb.stars.data.repository.StarWarsRepositoryImpl
import com.hb.stars.domain.repository.StarWarsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideAppRepository(
            api: StarWarsServices
    ): StarWarsRepository {
        val starWarsDataSourceImpl = StarWarsDataSourceImpl(api)
        return StarWarsRepositoryImpl(starWarsDataSourceImpl)
    }
}
