package me.baggi.schedule.config

import me.baggi.schedule.config.interceptor.Interceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val interceptor: Interceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor).addPathPatterns("/api/v1/admin/**")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Разрешить для всех путей
            .allowedOrigins("http://localhost:3000") // Указать разрешённый origin
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешить методы, включая OPTIONS
            .allowedHeaders("*") // Разрешить любые заголовки
            .allowCredentials(true) // Разрешить отправку куков
    }
}