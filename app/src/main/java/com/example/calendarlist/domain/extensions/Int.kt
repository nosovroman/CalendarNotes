package com.example.calendarlist.domain.extensions

fun Int.toTimeFormat(): String {
    return when {
        this < 9 -> "0$this:00 - 0${this+1}:00"
        this == 9 -> "09:00 - 10:00"
        else -> "$this:00 - ${this+1}:00"
    }
}