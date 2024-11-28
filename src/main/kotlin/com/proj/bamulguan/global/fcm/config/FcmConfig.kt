package com.proj.bamulguan.global.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.proj.bamulguan.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.ExecutionException


@Configuration
@EnableAsync
class FcmConfig(
    private val userRepository: UserRepository
) {

    @Value("\${fcm.key.path}")
    lateinit var FCM_PRIVATE_KEY_PATH: String

    @Value("\${fcm.key.scope}")
    lateinit var FCM_SCOPE: String

    fun init() {
        val options: FirebaseOptions = FirebaseOptions.Builder()
            .setCredentials(
                GoogleCredentials
                    .fromStream(ClassPathResource(FCM_PRIVATE_KEY_PATH).inputStream) // keyPath의 inputStream으로 인증정보 확인
                    .createScoped(listOf(FCM_SCOPE)) // Firebase Cloud Messaging(Firebase Scope)을 설정
            )
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }
    }

    @Async
    fun sendByTokenList(title: String, body: String) {
        val user = userRepository.findAll()

        val fcmTokenList = user
            .filter { it.fcmToken != null }
            .map { it.fcmToken!! }

        val batchSize = 100
        for (i in fcmTokenList.indices step batchSize) {
            val batchList = fcmTokenList.subList(i, (i + batchSize).coerceAtMost(fcmTokenList.size))
            sendBatchMessages(batchList, title, body)
        }
    }

    fun sendBatchMessages(fcmTokenList: List<String>, title: String, body: String) {
        val message = MulticastMessage.builder()
            .setNotification(
                Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()
            )
            .addAllTokens(fcmTokenList)
            .build()

            try {
                val response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
                handleResponse(response, fcmTokenList)
            } catch (e: ExecutionException) {
                throw RuntimeException(e)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                throw RuntimeException(e)
            }
    }

    private fun handleResponse(response: BatchResponse, fcmTokenList: List<String>) {
        if (response.failureCount > 0) {
            val responses = response.responses
            val failedTokens = mutableListOf<String>()

            responses.forEachIndexed { index, sendResponse ->
                if (!sendResponse.isSuccessful) {
                    failedTokens.add(fcmTokenList[index])
                }
            }
        }
    }
}