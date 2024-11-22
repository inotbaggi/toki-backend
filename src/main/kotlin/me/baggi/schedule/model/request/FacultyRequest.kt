package me.baggi.schedule.model.request

sealed class FacultyRequest {
    data class Create(
        val facultyName: String
    )
}