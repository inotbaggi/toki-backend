package me.baggi.schedule.service

import me.baggi.schedule.model.Faculty
import me.baggi.schedule.model.Group
import me.baggi.schedule.repository.FacultyRepository
import me.baggi.schedule.repository.GroupRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class FacultyService(
    private val facultyRepository: FacultyRepository,
    private val groupRepository: GroupRepository
) {
    fun createFaculty(facultyName: String) {
        facultyRepository.save(Faculty(name = facultyName))
    }

    fun saveFaculty(faculty: Faculty) {
        facultyRepository.save(faculty)
    }

    fun deleteFaculty(id: Long) {
        facultyRepository.deleteById(id)
    }

    fun createGroup(facultyId: Long, groupName: String) {
        val faculty = getFacultyById(facultyId).getOrNull() ?: throw Exception("Faculty not found!")
        faculty.groups.add(Group(name = groupName, faculty = faculty))
        facultyRepository.save(faculty)
    }

    fun getFacultyById(id: Long) = facultyRepository.findById(id)

    fun getAllFaculties(): List<Faculty> = facultyRepository.findAll()

    fun getGroupsByFacultyId(id: Long) = groupRepository.findGroupsByFacultyId(id)
}