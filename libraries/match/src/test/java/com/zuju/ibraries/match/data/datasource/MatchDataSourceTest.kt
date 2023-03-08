package com.zuju.ibraries.match.data.datasource

import com.zuju.ibraries.match.data.dto.MatchDto
import com.zuju.ibraries.match.data.dto.MatchResultDto
import com.zuju.ibraries.match.data.dto.PreviousMatchDto
import com.zuju.ibraries.match.data.dto.UpcomingMatchDto
import com.zuju.ibraries.match.data.network.MatchApiService
import com.zuju.ibraries.match.domain.model.MatchData
import com.zuju.ibraries.match.domain.model.PreviousMatch
import com.zuju.ibraries.match.domain.model.UpcomingMatch
import com.zuju.ibraries.match.domain.repository.MatchRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MatchDataSourceTest {

    @Mock
    private lateinit var matchApiService: MatchApiService

    private lateinit var matchDataRepository: MatchRepository

    private val previousDto = listOf(
        PreviousMatchDto(away = "aaa1", date = "bbb1", description = "ccc1", highlights = "ddd1", home = "eee1", winner = "fff1"),
        PreviousMatchDto(away = "aaa2", date = "bbb2", description = "ccc2", highlights = "ddd2", home = "eee2", winner = "fff2"),
        PreviousMatchDto(away = "aaa3", date = "bbb3", description = "ccc3", highlights = "ddd3", home = "eee3", winner = "fff3"),
        PreviousMatchDto(away = "aaa4", date = "bbb4", description = "ccc4", highlights = "ddd4", home = "eee4", winner = "fff4"),
    )

    private val upcomingDto = listOf(
        UpcomingMatchDto(away = "zzz1", date = "www1", description = "eee1", home = "rrr1"),
        UpcomingMatchDto(away = "zzz2", date = "www2", description = "eee2", home = "rrr2"),
        UpcomingMatchDto(away = "zzz3", date = "www3", description = "eee3", home = "rrr3"),
        UpcomingMatchDto(away = "zzz4", date = "www4", description = "eee4", home = "rrr4"),
    )

    private val mockData = MatchResultDto(MatchDto(previousDto, upcomingDto))

    @Before
    fun setUp() {
        matchDataRepository = MatchDataSource(matchApiService)
    }

    @Test
    fun getAllMatchSuccess() {
        val previous = listOf(
            PreviousMatch(away = "aaa1", date = "bbb1", description = "ccc1", highlights = "ddd1", home = "eee1", winner = "fff1"),
            PreviousMatch(away = "aaa2", date = "bbb2", description = "ccc2", highlights = "ddd2", home = "eee2", winner = "fff2"),
            PreviousMatch(away = "aaa3", date = "bbb3", description = "ccc3", highlights = "ddd3", home = "eee3", winner = "fff3"),
            PreviousMatch(away = "aaa4", date = "bbb4", description = "ccc4", highlights = "ddd4", home = "eee4", winner = "fff4"),
        )

        val upcoming = listOf(
            UpcomingMatch(away = "zzz1", date = "www1", description = "eee1", home = "rrr1"),
            UpcomingMatch(away = "zzz2", date = "www2", description = "eee2", home = "rrr2"),
            UpcomingMatch(away = "zzz3", date = "www3", description = "eee3", home = "rrr3"),
            UpcomingMatch(away = "zzz4", date = "www4", description = "eee4", home = "rrr4"),
        )

        val expectedResult = MatchData(previous, upcoming)

        runBlocking {
            whenever(matchApiService.getAllMatches()).thenReturn(mockData)
            val result = matchDataRepository.getMatch(null).first()
            assert(result == expectedResult)
        }
    }


    @Test
    fun getMatchByIdSuccess() {
        val previous = listOf(
            PreviousMatch(away = "aaa1", date = "bbb1", description = "ccc1", highlights = "ddd1", home = "eee1", winner = "fff1"),
            PreviousMatch(away = "aaa2", date = "bbb2", description = "ccc2", highlights = "ddd2", home = "eee2", winner = "fff2"),
            PreviousMatch(away = "aaa3", date = "bbb3", description = "ccc3", highlights = "ddd3", home = "eee3", winner = "fff3"),
            PreviousMatch(away = "aaa4", date = "bbb4", description = "ccc4", highlights = "ddd4", home = "eee4", winner = "fff4"),
        )

        val upcoming = listOf(
            UpcomingMatch(away = "zzz1", date = "www1", description = "eee1", home = "rrr1"),
            UpcomingMatch(away = "zzz2", date = "www2", description = "eee2", home = "rrr2"),
            UpcomingMatch(away = "zzz3", date = "www3", description = "eee3", home = "rrr3"),
            UpcomingMatch(away = "zzz4", date = "www4", description = "eee4", home = "rrr4"),
        )

        val expectedResult = MatchData(previous, upcoming)

        runBlocking {
            whenever(matchApiService.getMatchByTeam("aaa1")).thenReturn(mockData)
            val result = matchDataRepository.getMatch("aaa1").first()
            assert(result == expectedResult)
        }
    }
}
