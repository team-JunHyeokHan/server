package com.proj.bamulguan.domain.board.controller

import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.service.BoardService
import com.proj.bamulguan.global.response.BaseResponse
import com.proj.bamulguan.global.response.BaseResponseData
import com.proj.bamulguan.global.s3.S3Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.http.HttpClient

@RestController
@RequestMapping("/post")
class BoardController (
    private val boardService: BoardService,
    private val s3Service: S3Service
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

    @PostMapping("/file")
    fun upload(@RequestPart multipartFileList: List<MultipartFile>): BaseResponseData<List<String>> {
        val boards: MutableList<String> = ArrayList()
        for (multipartFile in multipartFileList) {
            boards.add(s3Service.upload(multipartFile, "밤물관")
            )
        }
        return BaseResponseData.ok("성공", boards)
    }

}