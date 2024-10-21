package me.baggi.schedule.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Schedule(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val time: String,
    val subject: String,
    val teacherName: String,
    val room: String,
    @ManyToOne
    @JoinColumn(name = "group_id")
    val group: Group
)

data class ScheduleDTO(
    val id: Long,
    val time: String,
    val subject: String,
    val teacherName: String,
    val room: String
)

fun Schedule.toDTO() = ScheduleDTO(
    id = this.id,
    time = this.time,
    subject = this.subject,
    teacherName = this.teacherName,
    room = this.room
)
