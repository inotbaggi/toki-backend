package me.baggi.schedule.controller

import me.baggi.schedule.model.FacultyDTO
import me.baggi.schedule.model.ScheduleDTO
import me.baggi.schedule.model.toDTO
import me.baggi.schedule.service.ScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService
) {

    @GetMapping("/faculties")
    fun getFaculties(): List<FacultyDTO> {
        return scheduleService.getAllFaculties().map { it.toDTO() }
    }

    @GetMapping("/group/{groupId}")
    fun getSchedulesByGroup(@PathVariable groupId: Long): List<ScheduleDTO> {
        return scheduleService.getSchedulesByGroup(groupId)
    }
}