package me.baggi.schedule.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "schedule_days")
data class ScheduleDay(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val time: LocalDate,
    val intervalId: Long,
    @ManyToOne
    @JoinColumn(name = "group_id")
    val group: Group,
    @OneToMany(mappedBy = "scheduleDay", cascade = [CascadeType.ALL])
    var lessons: MutableList<Lesson> = mutableListOf()
)

data class ScheduleDayDTO(
    val id: Long,
    val intervalId: Long,
    val time: LocalDate,
    val lessons: List<LessonDTO>
)

fun ScheduleDay.toDTO() = ScheduleDayDTO(
    id = this.id,
    intervalId = this.intervalId,
    time = this.time,
    lessons = this.lessons.map { it.toDTO() }
)
