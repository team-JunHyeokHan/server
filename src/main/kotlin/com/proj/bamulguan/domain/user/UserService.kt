package com.proj.bamulguan.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun addToken(token: String){
        userRepository.save(
            UserEntity(
                id = null,
                fcmToken = token
            )
        )
    }
}