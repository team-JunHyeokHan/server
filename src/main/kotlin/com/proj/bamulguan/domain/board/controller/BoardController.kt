package com.proj.bamulguan.domain.board.controller

import com.proj.bamulguan.domain.board.dto.req.BoardReq
import com.proj.bamulguan.domain.board.dto.res.BoardRes
import com.proj.bamulguan.domain.board.service.BoardService
import com.proj.bamulguan.global.response.BaseResponse
import com.proj.bamulguan.global.response.BaseResponseData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpClient

@RestController
@RequestMapping("/post")
class BoardController (
    private val boardService: BoardService
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