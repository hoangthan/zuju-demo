package com.zuju.data.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeFormatter {
    const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_HOUR = "MM/dd/yyy HH'h':mm"

    fun formatDateString(
        source: String,
        sourceFormat: String,
        destinationFormat: String,
    ): String? {
        val sourceDateFormat = SimpleDateFormat(sourceFormat, Locale.getDefault())

        val date = try {
            sourceDateFormat.parse(source)
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }

        val destDateFormat = SimpleDateFormat(destinationFormat, Locale.getDefault())
        return destDateFormat.format(date)
    }
}
