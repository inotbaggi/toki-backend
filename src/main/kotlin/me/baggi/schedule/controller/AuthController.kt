package me.baggi.schedule.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController {
    @Value("\${admin.token}")
    private lateinit var token: String

    @PostMapping("/login")
    fun tokenValid(@RequestBody request: String): Boolean {
        return token == request
    }
}