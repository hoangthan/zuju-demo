package com.zuju.features.match.upcoming.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.zuju.features.core.R
import com.zuju.features.match.R.string


class MatchNotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = System.currentTimeMillis().hashCode()

        createNotificationChannel(notificationManager)

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationContent())
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(R.drawable.img_small_logo)
            .setAutoCancel(false)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    private fun getNotificationTitle(): String {
        return applicationContext.getString(string.match_is_started)
    }

    private fun getNotificationContent(): String {
        val homeTeam = inputData.getString(KEY_HOME_TEAM)
        val awayTeam = inputData.getString(KEY_AWAY_TEAM)
        val templateString = applicationContext.getString(string.the_match_between)
        return String.format(templateString, homeTeam, awayTeam)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "2023"
        const val CHANNEL_NAME = "Zuju Notification"
        const val KEY_HOME_TEAM = "KEY_HOME_TEAM"
        const val KEY_AWAY_TEAM = "KEY_AWAY_TEAM"
    }
}
