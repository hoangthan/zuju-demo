package com.zuju.ibraries.match.data.network

import com.zuju.ibraries.match.data.dto.MatchResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchApiService {
    @GET("teams/matches")
    suspend fun getAllMatches(): MatchResultDto

    @GET("teams/{teamId}/matches")
    suspend fun getMatchByTeam(@Path("teamId") teamId: String): MatchResultDto
}
