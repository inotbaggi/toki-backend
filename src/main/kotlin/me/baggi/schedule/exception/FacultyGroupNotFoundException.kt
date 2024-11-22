package me.baggi.schedule.exception

class FacultyGroupNotFoundException(val id: Long) : RuntimeException(
    "FacultyGroup not found for id: $id"
)