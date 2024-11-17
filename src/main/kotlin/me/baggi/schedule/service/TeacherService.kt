package me.baggi.schedule.service

import me.baggi.schedule.model.Teacher
import me.baggi.schedule.model.TeacherDTO
import me.baggi.schedule.repository.TeacherRepository
import org.springframework.stereotype.Service

@Service
class TeacherService(
    private val teacherRepository: TeacherRepository
) {
    fun addTeacher(teacher: TeacherDTO) {
        teacherRepository.save(
            Teacher(
                id = teacher.id,
                firstName = teacher.firstName,
                lastName = teacher.lastName,
                patronymic = teacher.patronymic,
                lessons = teacher.lessons
            )
        )
    }

    fun updateTeacher(id: Long, teacher: TeacherDTO) {
        teacherRepository.save(
            Teacher(
                id = id,
                firstName = teacher.firstName,
                lastName = teacher.lastName,
                patronymic = teacher.patronymic,
                lessons = teacher.lessons
            )
        )
    }

    fun removeTeacher(id: Long) {
        teacherRepository.deleteById(id)
    }

    fun getTeachers(): List<Teacher> = teacherRepository.findAll()
}