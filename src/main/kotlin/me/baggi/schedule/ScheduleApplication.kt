package me.baggi.schedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class ScheduleApplication

fun main(args: Array<String>) {
    runApplication<ScheduleApplication>(*args)
}
