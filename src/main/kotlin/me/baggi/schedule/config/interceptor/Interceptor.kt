package me.baggi.schedule.config.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class Interceptor : HandlerInterceptor  {
    @Value("\${admin.token}")
    private lateinit var token: String

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method.equals("OPTIONS", ignoreCase = true)) {
            return true
        }
        val tokenHeader = request.getHeader("Token")
        if (tokenHeader == null || tokenHeader != token) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            return false
        }
        return true
    }
}