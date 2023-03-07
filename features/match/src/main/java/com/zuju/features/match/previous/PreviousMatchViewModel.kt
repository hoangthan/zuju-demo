package com.zuju.features.match.previous

import com.zuju.features.core.base.viewmodels.BaseViewModel
import com.zuju.features.match.previous.PreviousMatchViewModel.PreviousMatchViewEvent
import com.zuju.features.teamplayer.TeamPlayerUi
import kotlinx.coroutines.flow.MutableStateFlow

class PreviousMatchViewModel : BaseViewModel<PreviousMatchViewEvent>() {

    private val _currentTeamFlow: MutableStateFlow<TeamPlayerUi?> = MutableStateFlow(null)

    override fun dispatchEvent(event: PreviousMatchViewEvent) {
        when (event) {
            is PreviousMatchViewEvent.TeamChanged -> TODO()
        }
    }

    sealed interface PreviousMatchViewEvent {
        data class TeamChanged(val teamPlayerUi: TeamPlayerUi?) : PreviousMatchViewEvent
    }
}
