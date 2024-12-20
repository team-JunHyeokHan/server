package com.proj.bamulguan.domain.board.controller

import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.service.BoardService
import com.proj.bamulguan.domain.file.dto.FileDto
import com.proj.bamulguan.domain.file.service.FileService
import com.proj.bamulguan.global.response.BaseResponse
import com.proj.bamulguan.global.response.BaseResponseData
import com.proj.bamulguan.global.s3.S3Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.http.HttpClient

@RestController
@RequestMapping("/board")
class BoardController (
    private val boardService: BoardService,
){

    @PostMapping
    fun save(@RequestBody boardReq: BoardReq): BaseResponse{
        boardService.upload(boardReq)
        return BaseResponse.created("성공");
    }

    @GetMapping
    fun get(): BaseResponseData<MutableList<BoardRes>>{
        return BaseResponseData.ok("성공", boardService.get())
    }

}