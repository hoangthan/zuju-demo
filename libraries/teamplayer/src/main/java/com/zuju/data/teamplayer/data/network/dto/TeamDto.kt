package com.zuju.data.teamplayer.data.network.dto

import com.zuju.data.teamplayer.domain.usecase.TeamPlayer

data class TeamDto(
    val id: String,
    val name: String,
    val avatar: String,
)

fun TeamDto.asDomain(): TeamPlayer {
    return TeamPlayer(
        id = id,
        name = name,
        avatar = avatar,
    )
}
