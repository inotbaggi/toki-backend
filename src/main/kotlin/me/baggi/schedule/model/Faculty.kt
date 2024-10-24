package me.baggi.schedule.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity(name = "faculties")
data class Faculty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val name: String,
    @OneToMany(mappedBy = "faculty", cascade = [CascadeType.ALL])
    val groups: MutableList<Group> = mutableListOf()
)

data class FacultyDTO(
    val id: Long,
    val name: String
)

fun Faculty.toDTO() = FacultyDTO(
    id = this.id,
    name = this.name
)
