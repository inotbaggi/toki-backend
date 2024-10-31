package me.baggi.schedule.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream


@Configuration
class FirebaseConfig {
    @Bean
    fun initializeFirebase(): FirebaseApp {
        val serviceAccount =
            FileInputStream("firebase.json")

        val options: FirebaseOptions = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        return FirebaseApp.initializeApp(options)
    }
}