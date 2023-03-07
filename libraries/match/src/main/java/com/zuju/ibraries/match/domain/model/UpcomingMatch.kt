package com.zuju.ibraries.match.domain.model

data class UpcomingMatch(
    val away: String,
    val date: String,
    val description: String,
    val home: String,
    val homeLogo: String? = null,
    val awayLogo: String? = null,
)
