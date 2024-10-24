package me.baggi.schedule.service

import me.baggi.schedule.model.*
import me.baggi.schedule.model.request.ScheduleCreateRequest
import me.baggi.schedule.repository.GroupRepository
import me.baggi.schedule.repository.LessonTimeRepository
import me.baggi.schedule.repository.ScheduleDaysRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ScheduleService(
    private val facultyService: FacultyService,
    private val groupRepository: GroupRepository,
    private val scheduleDaysRepository: ScheduleDaysRepository,
    private val lessonTimeRepository: LessonTimeRepository
) {
    fun getSchedulesByGroup(groupId: Long): List<ScheduleDayDTO> {
        return scheduleDaysRepository.findAllByGroupId(groupId).map { it.toDTO() }
    }

    fun processScheduleCreating(request: ScheduleCreateRequest) {
        request.faculties.forEach {
            val faculty = facultyService.getFacultyById(it.id).getOrNull()
                ?: throw Exception("Faculty not found!")
            it.groups.forEach { groupPart ->
                val group = faculty.groups.find { it.id == groupPart.id }
                    ?: throw Exception("Group ${groupPart.id} not found! (Faculty: ${it.id})")
                val scheduleDay = ScheduleDay(time = request.date, group = group, intervalId = request.intervalId)
                scheduleDay.lessons = groupPart.lessons.map { it.toEntity(scheduleDay) }.toMutableList()
                group.schedules.add(scheduleDay)
            }
            facultyService.saveFaculty(faculty)
        }
    }

    fun getScheduleForDay(dayId: Long): ScheduleDay {
        return scheduleDaysRepository.findById(dayId).getOrNull()
            ?: throw Exception("ScheduleDay not found!")
    }

    fun createLessonTime(name: String, times: List<String>) {
        lessonTimeRepository.save(LessonTime(name = name, times = times))
    }

    fun getLessonTimes() = lessonTimeRepository.findAll()

    fun deleteLessonTime(id: Long) {
        lessonTimeRepository.deleteById(id)
    }
}