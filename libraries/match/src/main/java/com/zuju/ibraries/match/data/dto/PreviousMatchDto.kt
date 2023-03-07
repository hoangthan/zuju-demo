package com.zuju.ibraries.match.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zuju.ibraries.match.domain.model.PreviousMatch


@JsonClass(generateAdapter = true)
data class PreviousMatchDto(
    @Json(name = "away")
    val away: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "highlights")
    val highlights: String,
    @Json(name = "home")
    val home: String,
    @Json(name = "winner")
    val winner: String
)

fun PreviousMatchDto.asDomain(): PreviousMatch {
    return PreviousMatch(
        away = away,
        date = date,
        description = description,
        highlights = highlights,
        home = home,
        winner = winner,
    )
}
