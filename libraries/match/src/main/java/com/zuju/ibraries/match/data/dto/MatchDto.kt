package com.zuju.ibraries.match.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MatchDto(
    @Json(name = "previous")
    val previous: List<PreviousMatchDto>,

    @Json(name = "upcoming")
    val upcoming: List<UpcomingMatchDto>
)
