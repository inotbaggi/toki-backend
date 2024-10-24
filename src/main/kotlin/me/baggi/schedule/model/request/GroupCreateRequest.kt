package me.baggi.schedule.model.request

data class GroupCreateRequest(
    val facultyId: Long,
    val groupName: String
)
