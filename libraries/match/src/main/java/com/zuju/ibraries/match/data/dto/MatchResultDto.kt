package com.zuju.ibraries.match.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MatchResultDto(
    @Json(name = "matches")
    val matches: MatchDto
)
