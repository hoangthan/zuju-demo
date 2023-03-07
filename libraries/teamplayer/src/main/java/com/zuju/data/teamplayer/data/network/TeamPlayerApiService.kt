package com.zuju.data.teamplayer.data.network

import com.zuju.data.teamplayer.data.network.dto.TeamResultDto
import retrofit2.http.GET

interface TeamPlayerApiService {
    @GET("teams")
    suspend fun getTeams(): TeamResultDto
}
