package me.baggi.schedule.service

import me.baggi.schedule.model.Faculty
import me.baggi.schedule.model.ScheduleDTO
import me.baggi.schedule.model.toDTO
import me.baggi.schedule.repository.FacultyRepository
import me.baggi.schedule.repository.GroupRepository
import me.baggi.schedule.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val facultyRepository: FacultyRepository,
    private val groupRepository: GroupRepository,
    private val scheduleRepository: ScheduleRepository
) {
    fun getAllFaculties(): List<Faculty> = facultyRepository.findAll()

    fun getSchedulesByGroup(groupId: Long): List<ScheduleDTO> {
        return scheduleRepository.findAllByGroupId(groupId).map { it.toDTO() }
    }
}