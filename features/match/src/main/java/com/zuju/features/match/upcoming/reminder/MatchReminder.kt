package com.zuju.features.match.upcoming.reminder

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy.REPLACE
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.zuju.data.core.utils.DateTimeUtils
import com.zuju.features.match.upcoming.UpcomingMatchUi
import java.util.concurrent.TimeUnit

object MatchReminder {

    fun remind(context: Context, upcomingMatch: UpcomingMatchUi) {
        val gapTime = calculateTheTime(upcomingMatch)

        if (gapTime <= 0) return //Prevent data is invalid

        val inputData = Data.Builder()
            .putString(MatchNotificationWorker.KEY_HOME_TEAM, upcomingMatch.homeTeamName)
            .putString(MatchNotificationWorker.KEY_AWAY_TEAM, upcomingMatch.awayTeamName)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<MatchNotificationWorker>()
            .setInitialDelay(gapTime, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .build()

        //Every match has own information. So we can use toString() to combine all the field as an id
        val workId = upcomingMatch.toString()

        WorkManager.getInstance(context)
            .beginUniqueWork(workId, REPLACE, workRequest)
            .enqueue()
    }

    private fun calculateTheTime(upcomingMatch: UpcomingMatchUi): Long {
        val futureTime = DateTimeUtils.convertStringToMillis(
            source = upcomingMatch.date,
            format = DateTimeUtils.ISO_8601,
        ) ?: System.currentTimeMillis()

        return futureTime - System.currentTimeMillis()
    }
}
