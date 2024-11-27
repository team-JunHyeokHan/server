package com.proj.bamulguan.domain.board.entity

import com.proj.bamulguan.global.commoon.entity.BasTimeEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
data class BoardEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long?,

    @get:NotNull
    val title: String,

    @get:NotNull
    val content: String,

    ) : BasTimeEntity()