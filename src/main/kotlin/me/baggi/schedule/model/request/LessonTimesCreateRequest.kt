package me.baggi.schedule.model.request

data class LessonTimesCreateRequest(
    val name: String,
    val times: List<String>
)
