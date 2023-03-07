package com.zuju.ibraries.match.data.datasource

import com.zuju.ibraries.match.domain.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MatchDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindMatchDataSource(impl: MatchDataSource): MatchRepository
}
