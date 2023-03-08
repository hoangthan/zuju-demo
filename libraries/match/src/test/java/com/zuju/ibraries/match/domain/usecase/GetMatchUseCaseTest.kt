package com.zuju.ibraries.match.domain.usecase

import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import com.zuju.ibraries.match.domain.model.MatchData
import com.zuju.ibraries.match.domain.model.PreviousMatch
import com.zuju.ibraries.match.domain.model.UpcomingMatch
import com.zuju.ibraries.match.domain.repository.MatchRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMatchUseCaseTest {

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var teamPlayerRepository: TeamPlayerRepository

    private lateinit var getMatchUseCase: GetMatchUseCase

    private val mockTeam = listOf(
        TeamPlayer(id = "111", name = "111a", avatar = "avatar1"),
        TeamPlayer(id = "222", name = "222a", avatar = "avatar2"),
        TeamPlayer(id = "333", name = "333a", avatar = "avatar3"),
    )

    private val mockPreviousMatch = listOf(
        PreviousMatch(away = "111a", home = "222a",winner = "222a", date = "bbb1", description = "ccc1", highlights = "ddd1", awayLogo = null, homeLogo = null),
        PreviousMatch(away = "222a", home = "333a",winner = "222a", date = "bbb2", description = "ccc2", highlights = "111a", awayLogo = null, homeLogo = null),
        PreviousMatch(away = "333a", home = "111a",winner = "111a", date = "bbb3", description = "ccc3", highlights = "ddd3", awayLogo = null, homeLogo = null),
    )

    private val mockUpcomingMatch = listOf(
        UpcomingMatch(away = "111a", home = "222a", date = "www1", description = "eee1", awayLogo = null, homeLogo = null),
        UpcomingMatch(away = "222a", home = "333a", date = "www2", description = "eee2", awayLogo = null, homeLogo = null),
        UpcomingMatch(away = "333a", home = "111a", date = "www3", description = "111a", awayLogo = null, homeLogo = null),
    )

    private val mockMatchData = MatchData(previous = mockPreviousMatch, upcoming = mockUpcomingMatch)

    private val expectedPreviousMatch = listOf(
        PreviousMatch(away = "111a", home = "222a",winner = "222a", date = "bbb1", description = "ccc1", highlights = "ddd1", awayLogo = "avatar1", homeLogo = "avatar2"),
        PreviousMatch(away = "222a", home = "333a",winner = "222a", date = "bbb2", description = "ccc2", highlights = "111a", awayLogo = "avatar2", homeLogo = "avatar3"),
        PreviousMatch(away = "333a", home = "111a",winner = "111a", date = "bbb3", description = "ccc3", highlights = "ddd3", awayLogo = "avatar3", homeLogo = "avatar1"),
    )

    private val expectedUpcomingMatch = listOf(
        UpcomingMatch(away = "111a", home = "222a", date = "www1", description = "eee1", awayLogo = "avatar1", homeLogo = "avatar2"),
        UpcomingMatch(away = "222a", home = "333a", date = "www2", description = "eee2", awayLogo = "avatar2", homeLogo = "avatar3"),
        UpcomingMatch(away = "333a", home = "111a", date = "www3", description = "111a", awayLogo = "avatar3", homeLogo = "avatar1"),
    )

    private val expectedMatchData = MatchData(previous = expectedPreviousMatch, upcoming = expectedUpcomingMatch)

    @Before
    fun setUp() {
        getMatchUseCase = GetMatchUseCase(
            matchRepository = matchRepository,
            teamPlayerRepository = teamPlayerRepository,
        )

        teamPlayerRepository.getAllTeams()
    }

    @Test
    fun testAttachedDataFullTeam() {
        runBlocking {
            whenever(teamPlayerRepository.getAllTeams()).thenReturn(flowOf(mockTeam))
            whenever(matchRepository.getMatch(null)).thenReturn(flowOf(mockMatchData))

            val result = getMatchUseCase.invoke(GetMatchUseCase.GetMatchParam(null)).first()
            assert(result == expectedMatchData)
        }
    }

    @Test
    fun testAttachedDataATeam() {
        runBlocking {
            val teamName = "111a"
            whenever(teamPlayerRepository.getAllTeams()).thenReturn(flowOf(mockTeam))

            val filteredPreviousMatch = mockMatchData.previous.filter { it.home == teamName || it.away == teamName }
            val filteredUpcomingMatch = mockMatchData.upcoming.filter { it.home == teamName || it.away == teamName }
            val matchFilteredByTeam = mockMatchData.copy(filteredPreviousMatch, filteredUpcomingMatch)
            whenever(matchRepository.getMatch(teamName)).thenReturn(flowOf(matchFilteredByTeam))

            val expectPrevious = expectedMatchData.previous.filter { it.home == teamName || it.away == teamName }
            val expectUpcoming = expectedMatchData.upcoming.filter { it.home == teamName || it.away == teamName }
            val expectResult = expectedMatchData.copy(previous = expectPrevious, upcoming = expectUpcoming)

            val result = getMatchUseCase.invoke(GetMatchUseCase.GetMatchParam(teamName)).first()
            assert(result == expectResult)
        }
    }
}
