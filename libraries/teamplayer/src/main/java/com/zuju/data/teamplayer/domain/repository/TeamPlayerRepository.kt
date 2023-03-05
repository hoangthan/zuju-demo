package com.zuju.data.teamplayer.domain.repository

import com.zuju.data.teamplayer.domain.usecase.TeamPlayer
import kotlinx.coroutines.flow.Flow

interface TeamPlayerRepository {
    fun getAllTeams(): Flow<List<TeamPlayer>>
}
