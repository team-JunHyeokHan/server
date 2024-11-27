package com.proj.bamulguan.domain.board.service

import com.proj.bamulguan.domain.board.dto.mapper.BoardMapper
import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.entity.BoardEntity
import com.proj.bamulguan.domain.board.repository.BoardRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
){
    fun upload(boardReq: BoardReq){
        boardRepository.save(
            BoardMapper.toEntity(
                boardReq
            )
        )
    }

    fun get(): MutableList<BoardRes> {
        val boardList: List<BoardEntity> = boardRepository.findAll()
        val boards: MutableList<BoardRes> = ArrayList()
        for (board in boardList){
            boards.add(BoardMapper.toDomain(board))
        }
        return boards
    }
}