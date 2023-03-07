package com.zuju.data.teamplayer.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zuju.data.teamplayer.domain.usecase.TeamPlayer

@JsonClass(generateAdapter = true)
data class TeamDto(

    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "logo")
    val avatar: String,
)

fun TeamDto.asDomain(): TeamPlayer {
    return TeamPlayer(
        id = id,
        name = name,
        avatar = avatar,
    )
}
