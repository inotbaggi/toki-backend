package me.baggi.schedule.model.request

sealed class TeacherRequest {
    data class Create(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val patronymic: String,
        val lessons: List<String> = mutableListOf()
    )
}