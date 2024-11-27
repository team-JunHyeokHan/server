package com.proj.bamulguan.domain.user.controller

import com.proj.bamulguan.domain.user.dto.UserReq
import com.proj.bamulguan.domain.user.service.UserService
import com.proj.bamulguan.global.response.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun addToken(@RequestBody userReq: UserReq): BaseResponse{
        userService.addToken(userReq)
        return BaseResponse.created("성공")
    }
}