package com.proj.bamulguan.global.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.proj.bamulguan.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.Async
import java.time.LocalDateTime
import java.util.concurrent.ExecutionException

@Configuration
class FcmConfig(
    private val userRepository: UserRepository
) {

    @Value("\${fcm.key.path}")
    lateinit var FCM_PRIVATE_KEY_PATH: String

    @Value("\${fcm.key.scope}")
    lateinit var FCM_SCOPE: String

    fun init(){
        val options: FirebaseOptions = FirebaseOptions.Builder()
            .setCredentials(
                GoogleCredentials
                    .fromStream(ClassPathResource(FCM_PRIVATE_KEY_PATH).inputStream)//keyPath의 글 inputStream으로 인증정보 확인, inputStream으로 키 파일 불러오기
                    .createScoped(listOf(FCM_SCOPE)))//Firebase Cloud Messaging(Firebase Scope)을 설정
            .build()
        if (FirebaseApp.getApps().isEmpty()){ //FirebaseApp.getApps()를 사용하여 이미 초기화된 앱이 있는지 확인
            FirebaseApp.initializeApp(options) //초기화된 앱이 없으면 FirebaseApp.initializeApp(options)를 호출하여 앱을 초기화
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
        val messages = fcmTokenList.map { token ->
            require(!token.isNullOrEmpty())

            Message.builder()
                .putData("time", LocalDateTime.now().toString())
                .setNotification(
                    Notification.builder()  // builder 패턴을 사용하여 Notification 객체 생성
                        .setTitle(title)
                        .setBody(body)
                        .build()  // build() 메서드로 최종 Notification 객체 생성
                )
                .setToken(token)
                .build()
        }.filterNotNull()

        try {
            val response = FirebaseMessaging.getInstance().sendAllAsync(messages).get()
            handleResponse(response, fcmTokenList)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
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