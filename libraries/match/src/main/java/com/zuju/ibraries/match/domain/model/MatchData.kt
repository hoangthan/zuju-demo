package com.zuju.ibraries.match.domain.model


data class MatchData(
    val previous: List<PreviousMatch>,
    val upcoming: List<UpcomingMatch>
)
