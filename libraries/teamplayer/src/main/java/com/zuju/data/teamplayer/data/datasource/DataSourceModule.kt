package com.zuju.data.teamplayer.data.datasource

import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindTeamDataSource(impl: TeamPlayerDataSource): TeamPlayerRepository
}
