package me.baggi.schedule.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

sealed class ScheduleDayRequest {
    data class CreateRequest(
        val date: LocalDate,
        val intervalId: Long,
        val faculties: List<FacultyPart>
    ) {
        data class FacultyPart(
            val id: Long,
            @JsonProperty("name")
            val facultyName: String,
            val groups: List<GroupPart>
        )

        data class GroupPart(
            val id: Long,
            @JsonProperty("name")
            val groupName: String,
            val lessons: List<LessonPart>
        )

        data class LessonPart(
            val id: Long,
            @JsonProperty("name")
            val lessonName: String,
            val teachers: List<String>,
            val cabinets: List<String>
        )
    }
}