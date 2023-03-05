package com.zuju.data.teamplayer.data.datasource

import com.zuju.data.teamplayer.data.network.TeamPlayerApiService
import com.zuju.data.teamplayer.data.network.dto.asDomain
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import com.zuju.data.teamplayer.domain.usecase.TeamPlayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TeamPlayerDataSource @Inject constructor(
    private val apiService: TeamPlayerApiService,
) : TeamPlayerRepository {

    override fun getAllTeams(): Flow<List<TeamPlayer>> {
        return apiService.getTeams()
            .map { result -> result.teams.map { it.asDomain() } }
    }
}
