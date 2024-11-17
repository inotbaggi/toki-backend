package me.baggi.schedule.controller

import me.baggi.schedule.model.AppInfo
import me.baggi.schedule.service.AppService
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/app")
class AppController(
    private val appService: AppService
) {
    @GetMapping("/info")
    fun getAppInfo(): AppInfo = appService.getAppInfo()

    @GetMapping("/download")
    fun downloadApk(): ResponseEntity<FileSystemResource> {
        val file = appService.getApplicationFile() ?: return ResponseEntity.notFound().build()

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.filename)
        headers.add(HttpHeaders.CONTENT_LENGTH, file.contentAsByteArray.size.toString())
        return ResponseEntity.ok().headers(headers).body(file)
    }
}