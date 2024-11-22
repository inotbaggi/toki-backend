package me.baggi.schedule.model.request

sealed class LessonTimeRequest {
    data class Create(
        val name: String,
        val times: List<String>
    )
}