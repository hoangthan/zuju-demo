package com.zuju.features.teamplayer

import com.zuju.data.teamplayer.domain.model.TeamPlayer

data class TeamPlayerUi(
    val id: String,
    val name: String,
    val avatar: String,
)

fun TeamPlayer.toUiModel(): TeamPlayerUi {
    return TeamPlayerUi(
        id = id,
        name = name,
        avatar = avatar,
    )
}
