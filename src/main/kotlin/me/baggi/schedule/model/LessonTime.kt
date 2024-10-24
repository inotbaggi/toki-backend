package me.baggi.schedule.model

import jakarta.persistence.*

@Entity(name = "lesson_times")
data class LessonTime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val name: String,

    @ElementCollection
    val times: List<String>,
)

