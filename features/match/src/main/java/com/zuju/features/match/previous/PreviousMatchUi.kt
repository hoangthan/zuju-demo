package com.zuju.features.match.previous

data class PreviousMatchUi(
    val date: String,
    val description: String,
    val homeTeamName: String,
    val awayTeamName: String,
    val winner: String,
    val highlights: String,
    val homeTeamAvatar: String,
    val awayTeamAvatar: String,
)
