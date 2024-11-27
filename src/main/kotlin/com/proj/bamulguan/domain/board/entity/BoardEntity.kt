package com.proj.bamulguan.domain.board.entity

import com.proj.bamulguan.domain.file.entity.FileEntity
import com.proj.bamulguan.global.commoon.entity.BasTimeEntity
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
data class BoardEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    val id: Long?,

    @get:NotNull
    val title: String,

    val content: String?,

    @Column()
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    val image: List<FileEntity>?

    ) : BasTimeEntity()