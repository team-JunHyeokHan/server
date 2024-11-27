package com.proj.bamulguan.domain.board.dto.res

data class BoardRes(
    val id: Long?,
    val title: String,
    val content: String?,
    val imageUrl: String?
)