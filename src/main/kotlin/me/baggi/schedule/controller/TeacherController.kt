package me.baggi.schedule.controller

import me.baggi.schedule.model.Teacher
import me.baggi.schedule.model.TeacherDTO
import me.baggi.schedule.model.toDTO
import me.baggi.schedule.service.TeacherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/staff")
class TeacherController(
    private val teacherService: TeacherService
) {
    @GetMapping("/list")
    fun teachers(): List<TeacherDTO> = teacherService.getTeachers().map { it.toDTO() }
}