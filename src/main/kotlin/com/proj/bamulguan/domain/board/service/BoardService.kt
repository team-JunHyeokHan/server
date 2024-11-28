package com.proj.bamulguan.domain.board.service

import com.proj.bamulguan.domain.board.dto.mapper.BoardMapper
import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.entity.BoardEntity
import com.proj.bamulguan.domain.board.repository.BoardRepository
import com.proj.bamulguan.domain.file.repository.FileRepository
import com.proj.bamulguan.global.fcm.config.FcmConfig
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val fcmConfig: FcmConfig,
    private val fileRepository: FileRepository
){
    fun upload(boardReq: BoardReq) {
        val files = fileRepository.findAllById(boardReq.files ?: emptyList())
        boardRepository.save(
            BoardMapper.toEntity(
                boardReq,
                files
            )
        )
        fcmConfig.sendByTokenList("새로운 작품이 올라왔어요!", boardReq.title)
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