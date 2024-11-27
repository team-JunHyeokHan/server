package com.proj.bamulguan.domain.board.dto.mapper

import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.entity.BoardEntity

class BoardMapper {
    companion object{

        fun toEntity(boardReq: BoardReq): BoardEntity{
            return boardReq.let {
                BoardEntity(
                    id = null,
                    title = boardReq.title,
                    content = boardReq.content
                )
            }
        }

        fun toDomain(boardEntity: BoardEntity): BoardRes {
            return BoardRes(
                id = boardEntity.id,
                title = boardEntity.title,
                content = boardEntity.content
            )
        }

    }
}