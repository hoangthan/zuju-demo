package com.zuju.data.teamplayer.data.datasource

import com.zuju.data.teamplayer.data.network.TeamPlayerApiService
import com.zuju.data.teamplayer.data.network.dto.asDomain
import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamPlayerDataSource @Inject constructor(
    private val apiService: TeamPlayerApiService,
) : TeamPlayerRepository {

    //Assume the data of team player will not be changed frequently. So we gonna cache the loaded data once has the data
    private lateinit var cachedData: List<TeamPlayer>

    override fun getAllTeams(): Flow<List<TeamPlayer>> {
        return flow {
            if (!::cachedData.isInitialized) {
                val dtoResponse = apiService.getTeams()
                cachedData = dtoResponse.teams.map { it.asDomain() }
            }
            emit(cachedData)
        }
    }
}
