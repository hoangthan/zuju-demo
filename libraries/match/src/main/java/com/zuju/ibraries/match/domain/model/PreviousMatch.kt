package com.zuju.ibraries.match.domain.model


data class PreviousMatch(
    val away: String,
    val date: String,
    val description: String,
    val highlights: String,
    val home: String,
    val winner: String,
    val homeLogo: String? = null,
    val awayLogo: String? = null,
)
