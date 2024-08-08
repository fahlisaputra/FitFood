package com.example.fitfoood.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTime(
    private val dateTime : String
) {

    private val dateInstance : Date

    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        dateInstance = formatter.parse(dateTime)!!
    }

    private fun formatDate(format: String) : String {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(dateInstance)
    }
    fun getDate(): String {
        return formatDate("yyyy-MM-dd")
    }

    fun getTime(): String {
        return formatDate("HH:mm")
    }

    override fun toString(): String {
        return dateTime
    }

    companion object {
        fun now(): DateTime {
            val currentDateTime = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return DateTime(formatter.format(currentDateTime))
        }

        fun fromUTC(utcDateTime: String): DateTime {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dateInstance = formatter.parse(utcDateTime)!!
            val newFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return DateTime(newFormatter.format(dateInstance))
        }
    }
}