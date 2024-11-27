package com.proj.bamulguan.domain.board.dto.req

import org.jetbrains.annotations.NotNull

data class BoardReq (
    @NotNull
    val title: String,

    val content: String?,

    val files: List<Long>?
)