package com.zuju.data.teamplayer.data.network

import com.zuju.data.teamplayer.data.network.dto.TeamResultDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface TeamPlayerApiService {

    @GET("teams")
    fun getTeams(): Flow<TeamResultDto>
}
