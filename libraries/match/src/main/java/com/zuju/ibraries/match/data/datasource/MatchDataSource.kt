package com.zuju.ibraries.match.data.datasource

import com.zuju.ibraries.match.data.dto.MatchResultDto
import com.zuju.ibraries.match.data.dto.asDomain
import com.zuju.ibraries.match.data.network.MatchApiService
import com.zuju.ibraries.match.domain.model.MatchData
import com.zuju.ibraries.match.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MatchDataSource @Inject constructor(
    private val matchApiService: MatchApiService
) : MatchRepository {

    override fun getMatch(teamId: String?): Flow<MatchData> {
        return flow {
            val dtoResult = if (teamId == null) matchApiService.getAllMatches()
            else matchApiService.getMatchByTeam(teamId)

            val domainResult = convertToDomain(dtoResult)
            emit(domainResult)
        }
    }

    private fun convertToDomain(result: MatchResultDto): MatchData {
        val previousMatch = result.matches.previous.map { it.asDomain() }
        val upcomingMatch = result.matches.upcoming.map { it.asDomain() }
        return MatchData(
            previous = previousMatch,
            upcoming = upcomingMatch,
        )
    }
}
