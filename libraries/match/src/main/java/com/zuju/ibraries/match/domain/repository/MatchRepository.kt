package com.zuju.ibraries.match.domain.repository

import com.zuju.ibraries.match.domain.model.MatchData
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatch(teamId: String?): Flow<MatchData>
}
