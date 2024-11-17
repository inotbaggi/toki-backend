package me.baggi.schedule.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FirebaseService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendNotification(topic: String) {
        val message = Message.builder()
            .setTopic(topic)
            .setNotification(
                Notification.builder()
                    .setTitle("")
                    .setBody("")
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().send(message)
        logger.info("Sent notification to ($topic)")
    }
}