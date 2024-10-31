package me.baggi.schedule.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FirebaseService {
    fun sendNotification(topic: String) {
        val message = Message.builder()
            .setTopic(topic)
            .setNotification(Notification.builder().setTitle("").build())
            .build()

        val response = FirebaseMessaging.getInstance().send(message)
        println("Successfully sent message to topic: $response")
    }
}