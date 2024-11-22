package me.baggi.schedule.model.request

sealed class GroupRequest {
    data class Create(
        val facultyId: Long,
        val groupName: String
    )
}