package me.baggi.schedule.model

import jakarta.persistence.*
import me.baggi.schedule.model.request.LessonPart
import java.util.*

@Entity(name = "lessons")
data class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @ManyToOne
    @JoinColumn(name = "scheduleDay_id")
    val scheduleDay: ScheduleDay,

    @ElementCollection
    val teachers: List<String>,
    @ElementCollection
    val cabinets: List<String>
)

data class LessonDTO(
    val teachers: List<String>,
    val cabinets: List<String>
)

fun Lesson.toDTO() = LessonDTO(
    teachers = this.teachers,
    cabinets = this.cabinets
)

fun LessonPart.toEntity(day: ScheduleDay) = Lesson(
    scheduleDay = day,
    teachers = this.teachers,
    cabinets = this.cabinets
)
