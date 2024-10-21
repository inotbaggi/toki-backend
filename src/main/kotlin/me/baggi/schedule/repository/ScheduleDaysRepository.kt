package me.baggi.schedule.repository

import me.baggi.schedule.model.ScheduleDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository : JpaRepository<ScheduleDay, Long> {
    fun findAllByGroupId(groupId: Long): List<ScheduleDay>
}