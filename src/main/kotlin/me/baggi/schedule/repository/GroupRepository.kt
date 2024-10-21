package me.baggi.schedule.repository

import me.baggi.schedule.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : JpaRepository<Group, Long>