package com.proj.bamulguan.domain.user.service

import com.proj.bamulguan.domain.user.dto.UserReq
import com.proj.bamulguan.domain.user.entity.UserEntity
import com.proj.bamulguan.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun addToken(userReq: UserReq){
        userRepository.save(
            UserEntity(
                id = null,
                fcmToken = userReq.token
            )
        )
    }
}