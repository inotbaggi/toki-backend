package me.baggi.schedule.repository

import me.baggi.schedule.model.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FacultyRepository : JpaRepository<Faculty, Long>