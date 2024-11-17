package me.baggi.schedule.repository

import me.baggi.schedule.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<Teacher, Long>