package com.proj.bamulguan.domain.user.entity

import com.proj.bamulguan.global.commoon.entity.BasTimeEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long?,

    @get:NotNull
    val fcmToken: String

) : BasTimeEntity()
