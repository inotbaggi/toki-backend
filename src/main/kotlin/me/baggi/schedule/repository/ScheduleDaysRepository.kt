package me.baggi.schedule.repository

import me.baggi.schedule.model.ScheduleDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ScheduleDaysRepository : JpaRepository<ScheduleDay, Long> {
    fun findAllByGroupId(groupId: Long): List<ScheduleDay>

    @Query("SELECT sd FROM schedule_days sd WHERE sd.group.id = :groupId AND FUNCTION('DATE', sd.time) = FUNCTION('DATE', CURRENT_DATE)")
    fun getTodayScheduleDayForGroup(@Param("groupId") groupId: Long): ScheduleDay?
}