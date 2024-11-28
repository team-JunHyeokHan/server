package com.proj.bamulguan

import com.proj.bamulguan.global.fcm.config.FcmConfig
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class BamulguanApplication(
    private val fcmConfig: FcmConfig
) {

    @PostConstruct
    fun initializeFirebase() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        fcmConfig.init()
    }
}

fun main(args: Array<String>) {
    runApplication<BamulguanApplication>(*args)
}