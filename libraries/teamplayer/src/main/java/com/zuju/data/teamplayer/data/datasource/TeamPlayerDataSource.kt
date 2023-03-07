package com.zuju.data.teamplayer.data.datasource

import com.zuju.data.teamplayer.data.network.TeamPlayerApiService
import com.zuju.data.teamplayer.data.network.dto.asDomain
import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class TeamPlayerDataSource @Inject constructor(
    private val apiService: TeamPlayerApiService,
) : TeamPlayerRepository {

    //Assume the data of team player will not be changed frequently. So we gonna cache the loaded data as the sharedFlow
    @OptIn(DelicateCoroutinesApi::class)
    private val cachedFlow = flow {
        val dtoResponse = apiService.getTeams()
        val result = dtoResponse.teams.map { it.asDomain() }
        emit(result)
    }.shareIn(
        replay = 1,
        scope = GlobalScope,
        started = SharingStarted.WhileSubscribed(),
    )

    override fun getAllTeams(): Flow<List<TeamPlayer>> {
        return cachedFlow
    }
}
