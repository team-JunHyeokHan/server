package com.proj.bamulguan.domain.file.entity

import com.proj.bamulguan.domain.board.entity.BoardEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
data class FileEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    val id: Long?,

    @get:NotNull
    @Column(columnDefinition = "TEXT")
    val url: String
)