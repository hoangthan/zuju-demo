package com.zuju.features.match.sharedviewmodel

import androidx.lifecycle.viewModelScope
import com.zuju.data.core.model.asResult
import com.zuju.data.core.model.mapResult
import com.zuju.features.core.base.viewmodels.BaseViewModel
import com.zuju.features.match.previous.toUi
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel.MatchViewEvent
import com.zuju.features.match.upcoming.toUi
import com.zuju.features.teamplayer.TeamPlayerUi
import com.zuju.ibraries.match.domain.usecase.GetMatchUseCase
import com.zuju.ibraries.match.domain.usecase.GetMatchUseCase.GetMatchParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MatchSharedViewModel @Inject constructor(
    private val getMatch: GetMatchUseCase
) : BaseViewModel<MatchViewEvent>() {

    private val _currentTeamFlow: MutableStateFlow<TeamPlayerUi?> = MutableStateFlow(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _matchFlow = _currentTeamFlow
        .flatMapLatest { getMatch(GetMatchParam(it?.id)) }
        .asResult()
        .shareIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(), replay = 1)

    val matchFlow = _matchFlow

    val previousMatchFlow = _matchFlow.mapResult { match ->
        match.previous.map { it.toUi() }
    }

    val upcomingMatchFlow = _matchFlow.mapResult { match ->
        match.upcoming.map { it.toUi() }
    }

    val currentTeamFlow = _currentTeamFlow.asStateFlow()

    override fun dispatchEvent(event: MatchViewEvent) {
        when (event) {
            is MatchViewEvent.ChangeTeam -> changeTeam(event.team)
        }
    }

    private fun changeTeam(team: TeamPlayerUi?) {
        _currentTeamFlow.update { team }
    }

    sealed interface MatchViewEvent {
        data class ChangeTeam(val team: TeamPlayerUi?) : MatchViewEvent
    }
}
