package com.proj.bamulguan.domain.board.dto.res

import com.proj.bamulguan.domain.file.entity.FileEntity

data class BoardRes(
    val id: Long?,
    val title: String,
    val content: String?,
    val imageUrl: List<FileEntity>?
)