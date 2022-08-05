package com.rahul.newsdemo.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val DATE_TIME_PATTERN_NEWS = "hh:mm a dd MMM yyyy"

fun String.formatTime(): String {
    val instant = Instant.parse(this)
    return instant.toNewsListFormat()
}

fun Instant.toNewsListFormat(): String {
    return this.formatToString(DATE_TIME_PATTERN_NEWS)
}

fun Instant.formatToString(pattern: String): String =
    ZonedDateTime.ofInstant(this, ZoneId.systemDefault()).formatToString(pattern)

fun ZonedDateTime.formatToString(pattern: String): String = format(pattern.dateTimeFormatter())

fun String.dateTimeFormatter(): DateTimeFormatter? = try {
    DateTimeFormatter.ofPattern(this, Locale.getDefault())
} catch (e: Exception) {
    null
}