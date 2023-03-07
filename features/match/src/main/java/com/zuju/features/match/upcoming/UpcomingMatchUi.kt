package com.zuju.features.match.upcoming

import com.zuju.ibraries.match.domain.model.UpcomingMatch

data class UpcomingMatchUi(
    val date: String,
    val description: String,
    val homeTeamName: String,
    val awayTeamName: String,
    val homeTeamAvatar: String?,
    val awayTeamAvatar: String?,
)

fun UpcomingMatch.toUi(): UpcomingMatchUi {
    return UpcomingMatchUi(
        date = date,
        description = description,
        homeTeamName = home,
        awayTeamName = away,
        homeTeamAvatar = homeLogo,
        awayTeamAvatar = awayLogo
    )
}
