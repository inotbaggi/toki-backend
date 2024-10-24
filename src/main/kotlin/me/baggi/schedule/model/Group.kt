package me.baggi.schedule.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity(name = "groups")
data class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    val faculty: Faculty? = null,
    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL])
    val schedules: MutableList<ScheduleDay> = mutableListOf()
)

data class GroupDTO(
    val id: Long,
    val name: String
)

fun Group.toDTO() = GroupDTO(
    id = this.id,
    name = this.name
)