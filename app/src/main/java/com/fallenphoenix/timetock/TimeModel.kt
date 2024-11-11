package com.fallenphoenix.timetock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimeModel {
    private val _seconds = MutableStateFlow("")
    private val seconds: StateFlow<String> = _seconds
    fun onSecondsChanged(seconds: String) {
        _seconds.value = seconds
    }

    private val _minutes = MutableStateFlow("")
    private val minutes: StateFlow<String> = _minutes
    fun onMinutesChanged(minutes: String) {
        _minutes.value = minutes
    }

    private val _hours = MutableStateFlow("")
    private val hours: StateFlow<String> = _hours
    fun onHoursChanged(hours: String) {
        _hours.value = hours
    }

    /**
     * Gets the full time remaining in seconds.
     *
     * It's achieved by summing up the hours, minutes and seconds.
     * Everything is being converted to seconds.
     */
    fun getFullTimeSeconds(): Int {
        var result = 0

        result += seconds.value.toInt()
        result += minutes.value.toInt() * 60
        result += hours.value.toInt() * 3600

        return result
    }




}