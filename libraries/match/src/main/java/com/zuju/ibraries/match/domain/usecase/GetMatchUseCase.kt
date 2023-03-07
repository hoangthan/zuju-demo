package com.zuju.ibraries.match.domain.usecase

import com.zuju.data.core.usecase.FlowUseCase
import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import com.zuju.ibraries.match.domain.model.MatchData
import com.zuju.ibraries.match.domain.repository.MatchRepository
import com.zuju.ibraries.match.domain.usecase.GetMatchUseCase.GetMatchParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val teamPlayerRepository: TeamPlayerRepository,
) : FlowUseCase<GetMatchParam, MatchData> {

    override fun invoke(param: GetMatchParam): Flow<MatchData> {
        val matchFlow = matchRepository.getMatch(param.teamId)
        val teamFlow = teamPlayerRepository.getAllTeams()
        return combine(matchFlow, teamFlow, ::attachTeamDetails)
    }

    private fun attachTeamDetails(match: MatchData, team: List<TeamPlayer>): MatchData {
        //Currently, the match data have no team ID. So we gonna assume that every team has the unique name. So we gonna attach the image of team to match data
        val mapTeamNameAvatar = team.associate { Pair(it.name, it.avatar) }

        val attachedUpcoming = match.upcoming.map {
            it.copy(
                homeLogo = mapTeamNameAvatar[it.home],
                awayLogo = mapTeamNameAvatar[it.away],
            )
        }

        val attachedPrevious = match.previous.map {
            it.copy(
                homeLogo = mapTeamNameAvatar[it.home],
                awayLogo = mapTeamNameAvatar[it.away],
            )
        }

        return match.copy(
            previous = attachedPrevious,
            upcoming = attachedUpcoming,
        )
    }

    data class GetMatchParam(
        val teamId: String? = null,
    )
}
