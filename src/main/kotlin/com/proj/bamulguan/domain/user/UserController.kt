package com.proj.bamulguan.domain.user

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
    fun addToken(@RequestBody token:String): BaseResponse{
        userService.addToken(token)
        return BaseResponse.created("성공")
    }
}