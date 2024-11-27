package com.proj.bamulguan.domain.user.repository

import com.proj.bamulguan.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
}