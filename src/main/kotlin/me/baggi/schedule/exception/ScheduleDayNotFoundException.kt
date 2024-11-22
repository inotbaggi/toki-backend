package me.baggi.schedule.exception

class ScheduleDayNotFoundException(val id: Long) : RuntimeException(
    "ScheduleDay not found for id: $id"
)