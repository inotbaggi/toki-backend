package me.baggi.schedule.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Teacher(
    @Id
    @GeneratedValue
    val id: Long,

    val firstName: String,
    val lastName: String,
    val patronymic: String,

    @ElementCollection
    val lessons: List<String> = mutableListOf()
)

data class TeacherDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val lessons: List<String> = mutableListOf()
)

fun Teacher.toDTO() = TeacherDTO(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    patronymic = this.patronymic,
    lessons = this.lessons
)