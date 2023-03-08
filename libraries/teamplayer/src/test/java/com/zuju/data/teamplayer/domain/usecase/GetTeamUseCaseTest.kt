package com.zuju.data.teamplayer.domain.usecase

import com.zuju.data.teamplayer.domain.model.TeamPlayer
import com.zuju.data.teamplayer.domain.repository.TeamPlayerRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetTeamUseCaseTest {

    @Mock
    private lateinit var teamPlayerRepository: TeamPlayerRepository

    private lateinit var useCase: GetTeamUseCase

    @Before
    fun setUp() {
        useCase = GetTeamUseCase(teamPlayerRepository)
    }

    @Test
    fun testGetDataSuccess() {
        val expectedData = listOf(
            TeamPlayer(id = "111", name = "111a", avatar = "avatar"),
            TeamPlayer(id = "222", name = "222a", avatar = "avatar"),
            TeamPlayer(id = "333", name = "333a", avatar = "avatar"),
            TeamPlayer(id = "444", name = "444a", avatar = "avatar"),
        )
        whenever(teamPlayerRepository.getAllTeams()).thenReturn(flowOf(expectedData))

        runBlocking {
            val result = useCase.invoke(GetTeamUseCase.GetTeamParam()).first()
            assert(result == expectedData)
        }
    }

    @Test
    fun testGetDataError() {
        whenever(teamPlayerRepository.getAllTeams()).thenThrow(RuntimeException("Internet has issues"))
        assertThrows(RuntimeException::class.java) {
            runBlocking {
                useCase.invoke(GetTeamUseCase.GetTeamParam()).first()
            }
        }
    }
}
