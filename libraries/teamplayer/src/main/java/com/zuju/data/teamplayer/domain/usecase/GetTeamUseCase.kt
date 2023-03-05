package com.zuju.data.teamplayer.domain.usecase

import com.zuju.data.core.usecase.FlowUseCase
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import com.zuju.data.teamplayer.domain.usecase.GetTeamUseCase.GetTeamParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val teamPlayerRepository: TeamPlayerRepository
) : FlowUseCase<GetTeamParam, List<TeamPlayer>> {

    override fun invoke(param: GetTeamParam): Flow<List<TeamPlayer>> {
        return teamPlayerRepository.getAllTeams()
    }

    class GetTeamParam
}
