package com.proj.bamulguan.domain.board.dto.req

import org.jetbrains.annotations.NotNull

data class BoardReq (
    @NotNull
    val title: String,
    @NotNull
    val content: String
)