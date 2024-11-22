package me.baggi.schedule.exception

class FacultyNotFoundException(val id: Long) : RuntimeException(
    "Faculty not found for id: $id"
)