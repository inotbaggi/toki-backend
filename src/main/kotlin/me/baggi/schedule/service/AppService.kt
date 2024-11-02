package me.baggi.schedule.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.baggi.schedule.model.AppInfo
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Service
import java.io.File

@Service
class AppService {
    private val objectMapper = jacksonObjectMapper()
    private val appInfoFile = File("appinfo.json")

    init {
        if (!appInfoFile.exists()) {
            appInfoFile.createNewFile()
            appInfoFile.writeText(objectMapper.writeValueAsString(AppInfo(
                "1.0.0",
                listOf("Кек лол")
            )))
        }
    }

    fun getApplicationFile(): FileSystemResource? {
        val version = getAppInfo().lastVersion
        val apk = File("android-$version.apk")
        if (!apk.exists()) {
            return null
        }

        return FileSystemResource(apk)
    }

    fun getAppInfo(): AppInfo = objectMapper.readValue(appInfoFile, AppInfo::class.java)
}