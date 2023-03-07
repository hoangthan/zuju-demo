package com.zuju.ibraries.match.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zuju.ibraries.match.domain.model.UpcomingMatch


@JsonClass(generateAdapter = true)
data class UpcomingMatchDto(
    @Json(name = "away")
    val away: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "home")
    val home: String
)

fun UpcomingMatchDto.asDomain(): UpcomingMatch {
    return UpcomingMatch(
        away = away,
        date = date,
        description = description,
        home = home,
    )
}
