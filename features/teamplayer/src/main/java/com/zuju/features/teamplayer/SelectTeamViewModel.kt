package com.zuju.features.teamplayer

import androidx.lifecycle.viewModelScope
import com.zuju.data.core.model.Result
import com.zuju.data.core.model.asResult
import com.zuju.data.teamplayer.domain.usecase.GetTeamUseCase
import com.zuju.data.teamplayer.domain.usecase.GetTeamUseCase.GetTeamParam
import com.zuju.features.core.base.viewmodels.BaseViewModel
import com.zuju.features.teamplayer.SelectTeamViewModel.SelectTeamEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    getTeams: GetTeamUseCase
) : BaseViewModel<SelectTeamEvent>() {

    private val _teamFlow = getTeams(GetTeamParam())
        .map { teams -> teams.map { it.toUiModel() } }
        .flowOn(Dispatchers.IO)
        .asResult()
        .stateIn(
            scope = viewModelScope,
            initialValue = Result.Loading,
            started = SharingStarted.WhileSubscribed(),
        )

    val teamFlow: Flow<Result<List<TeamPlayerUi>>> = _teamFlow

    override fun dispatchEvent(event: SelectTeamEvent) {

    }

    sealed class SelectTeamEvent
}
