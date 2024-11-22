package me.baggi.schedule.service

import me.baggi.schedule.exception.FacultyGroupNotFoundException
import me.baggi.schedule.exception.FacultyNotFoundException
import me.baggi.schedule.exception.ScheduleDayNotFoundException
import me.baggi.schedule.model.*
import me.baggi.schedule.model.request.ScheduleDayRequest
import me.baggi.schedule.repository.LessonTimeRepository
import me.baggi.schedule.repository.ScheduleDaysRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@Service
class ScheduleService(
    private val facultyService: FacultyService,
    private val firebaseService: FirebaseService,
    private val scheduleDaysRepository: ScheduleDaysRepository,
    private val lessonTimeRepository: LessonTimeRepository
) {
    fun getSchedulesByGroup(groupId: Long): List<ScheduleDayDTO> {
        return scheduleDaysRepository.findAllByGroupId(groupId).map { it.toDTO() }
    }

    fun processScheduleCreating(request: ScheduleDayRequest.CreateRequest) {
        request.faculties.forEach { facultyPart ->
            val faculty = facultyService.getFacultyById(facultyPart.id).getOrNull()
                ?: throw FacultyNotFoundException(facultyPart.id)

            facultyPart.groups.forEach { groupPart ->
                val group = faculty.groups.find { it.id == groupPart.id }
                    ?: throw FacultyGroupNotFoundException(groupPart.id)

                val scheduleDay = ScheduleDay(time = request.date, group = group, intervalId = request.intervalId)
                scheduleDay.lessons = groupPart.lessons.map { it.toEntity(scheduleDay) }.toMutableList()

                group.schedules.add(scheduleDay)
            }

            facultyService.saveFaculty(faculty)

            firebaseService.sendNotification("schedule-update")
        }
    }

    fun getTodayScheduleDayForGroup(groupId: Long): ScheduleDayDTO? =
        scheduleDaysRepository.getTodayScheduleDayForGroup(groupId)?.toDTO()

    fun getScheduleForDay(dayId: Long): ScheduleDay {
        return scheduleDaysRepository.findById(dayId).getOrNull()
            ?: throw ScheduleDayNotFoundException(dayId)
    }

    fun getSchedulesForWeek(): List<ScheduleDay> {
        val start = LocalDate.now()
        val end = start.plusDays(7)
        return scheduleDaysRepository.findByDateRange(start, end)
    }

    fun createLessonTime(name: String, times: List<String>) {
        lessonTimeRepository.save(LessonTime(name = name, times = times))
    }

    fun getLessonTimes() = lessonTimeRepository.findAll()

    fun deleteLessonTime(id: Long) {
        lessonTimeRepository.deleteById(id)
    }

    fun removeSchedule(id: Long) {
        scheduleDaysRepository.deleteById(id)
    }
}