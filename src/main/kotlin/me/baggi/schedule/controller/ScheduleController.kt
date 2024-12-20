package me.baggi.schedule.controller

import me.baggi.schedule.model.*
import me.baggi.schedule.service.FacultyService
import me.baggi.schedule.service.ScheduleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService,
    private val facultyService: FacultyService
) {

    @GetMapping("/faculty/list")
    fun getFaculties(): List<FacultyDTO> {
        return facultyService.getAllFaculties().map { it.toDTO() }
    }

    @GetMapping("/faculty/{id}/groups")
    fun getFacultyGroups(@PathVariable id: Long): List<GroupDTO> {
        return facultyService.getGroupsByFacultyId(id).map { it.toDTO() }
    }

    @GetMapping("/group/{groupId}")
    fun getScheduleDaysByGroup(@PathVariable groupId: Long): List<ScheduleDayDTO> {
        return scheduleService.getSchedulesByGroup(groupId)
    }

    @GetMapping("/group/{groupId}/today")
    fun getTodaySchedulesForGroup(@PathVariable groupId: Long): ResponseEntity<ScheduleDayDTO?> {
        return scheduleService.getTodayScheduleDayForGroup(groupId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/group/{groupId}/schedules")
    fun getSchedulesForGroup(@PathVariable groupId: Long, @RequestParam days: Int): List<ScheduleDayDTO> {
        return scheduleService.getSchedulesForWeek().map { it.toDTO() }
    }

    @GetMapping("/day/{dayId}")
    fun getScheduleDayById(@PathVariable dayId: Long): ResponseEntity<ScheduleDayDTO> {
        return try {
            ResponseEntity.ok(scheduleService.getScheduleForDay(dayId).toDTO())
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/times")
    fun getLessonTimes(): MutableList<LessonTime> {
        return scheduleService.getLessonTimes()
    }
}