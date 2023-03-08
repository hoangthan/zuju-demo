package com.zuju.data.teamplayer.data.datasource

import com.zuju.data.teamplayer.data.network.TeamPlayerApiService
import com.zuju.data.teamplayer.data.network.dto.TeamDto
import com.zuju.data.teamplayer.data.network.dto.TeamResultDto
import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TeamPlayerDataSourceTest {

    lateinit var dataSource: TeamPlayerRepository

    @Mock
    private lateinit var teamPlayerApiService: TeamPlayerApiService

    @Before
    fun setUp() {
        dataSource = TeamPlayerDataSource(teamPlayerApiService)
    }

    @Test
    fun getAllTeamsSuccess() {
        val mockData = TeamResultDto(
            teams = listOf(
                TeamDto(id = "111", name = "111a", avatar = "avatar"),
                TeamDto(id = "222", name = "222a", avatar = "avatar"),
                TeamDto(id = "333", name = "333a", avatar = "avatar"),
                TeamDto(id = "444", name = "444a", avatar = "avatar"),
            )
        )

        val expectedData = listOf(
            TeamPlayer(id = "111", name = "111a", avatar = "avatar"),
            TeamPlayer(id = "222", name = "222a", avatar = "avatar"),
            TeamPlayer(id = "333", name = "333a", avatar = "avatar"),
            TeamPlayer(id = "444", name = "444a", avatar = "avatar"),
        )

        runBlocking {
            whenever(teamPlayerApiService.getTeams()).thenReturn(mockData)
            val result = dataSource.getAllTeams().first()
            assert(result == expectedData)
        }
    }

    @Test
    fun getAllTeamsMappingIncorrect() {
        val mockData = TeamResultDto(
            teams = listOf(
                TeamDto(id = "111", name = "111a", avatar = "avatar"),
                TeamDto(id = "222", name = "222a", avatar = "avatar"),
                TeamDto(id = "333", name = "333a", avatar = "avatar"),
                TeamDto(id = "444", name = "444a", avatar = "avatar"),
            )
        )

        val expectedData = listOf(
            TeamPlayer(id = "111a", name = "111", avatar = "avatar"),
            TeamPlayer(id = "222a", name = "222", avatar = "avatar"),
            TeamPlayer(id = "333a", name = "333", avatar = "avatar"),
            TeamPlayer(id = "444a", name = "444", avatar = "avatar"),
        )

        runBlocking {
            whenever(teamPlayerApiService.getTeams()).thenReturn(mockData)
            val result = dataSource.getAllTeams().first()
            assert(result != expectedData)
        }
    }
}
