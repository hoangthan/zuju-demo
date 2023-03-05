package com.zuju.data.teamplayer.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamResultDto(
    @Json(name = "teams")
    val teams: List<TeamDto>,
)
