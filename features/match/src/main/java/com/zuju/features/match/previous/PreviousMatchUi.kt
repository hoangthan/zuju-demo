package com.zuju.features.match.previous

import com.zuju.ibraries.match.domain.model.PreviousMatch

data class PreviousMatchUi(
    val date: String,
    val description: String,
    val homeTeamName: String,
    val awayTeamName: String,
    val winner: String,
    val highlights: String,
    val homeTeamAvatar: String?,
    val awayTeamAvatar: String?,
)

fun PreviousMatch.toUi(): PreviousMatchUi {
    return PreviousMatchUi(
        date = date,
        description = description,
        homeTeamName = home,
        awayTeamName = away,
        winner = winner,
        highlights = highlights,
        homeTeamAvatar = homeLogo,
        awayTeamAvatar = awayLogo,
    )
}
