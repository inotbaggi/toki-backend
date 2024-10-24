package me.baggi.schedule.repository

import me.baggi.schedule.model.LessonTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonTimeRepository : JpaRepository<LessonTime, Long>