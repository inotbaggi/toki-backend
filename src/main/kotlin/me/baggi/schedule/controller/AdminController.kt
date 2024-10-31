package me.baggi.schedule.controller

import me.baggi.schedule.model.request.FacultyCreateRequest
import me.baggi.schedule.model.request.GroupCreateRequest
import me.baggi.schedule.model.request.LessonTimesCreateRequest
import me.baggi.schedule.model.request.ScheduleCreateRequest
import me.baggi.schedule.service.FacultyService
import me.baggi.schedule.service.FirebaseService
import me.baggi.schedule.service.ScheduleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/admin")
class AdminController(
    private val facultyService: FacultyService,
    private val scheduleService: ScheduleService,
    private val firebaseService: FirebaseService
) {
    @PostMapping("/faculty/create")
    fun createFaculty(@RequestBody request: FacultyCreateRequest): ResponseEntity<Boolean> {
        return try {
            facultyService.createFaculty(request.facultyName)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(false)
        }
    }

    @DeleteMapping("/faculty/delete/{id}")
    fun deleteFaculty(@PathVariable id: Long): ResponseEntity<Boolean> {
        return try {
            facultyService.deleteFaculty(id)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(false)
        }
    }

    @PostMapping("/group/create")
    fun createGroup(@RequestBody request: GroupCreateRequest): ResponseEntity<Boolean> {
        return try {
            facultyService.createGroup(request.facultyId, request.groupName)
            ResponseEntity.ok(true)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(false)
        }
    }

    @PostMapping("/notify/send")
    fun sendNotify() {
        firebaseService.sendNotification("schedule-update")
    }

    @PostMapping("/schedule/create")
    fun createSchedule(@RequestBody request: ScheduleCreateRequest) {
        scheduleService.processScheduleCreating(request)
    }

    @PostMapping("/times/create")
    fun createTimes(@RequestBody request: LessonTimesCreateRequest) {
        scheduleService.createLessonTime(request.name, request.times)
    }

    @DeleteMapping("/times/delete/{id}")
    fun deleteTime(@PathVariable id: Long) {
        scheduleService.deleteLessonTime(id)
    }
}