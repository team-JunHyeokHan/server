package com.proj.bamulguan.global.response

import org.springframework.http.HttpStatus

data class BaseResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(status: HttpStatus, message: String): BaseResponse {
            return BaseResponse(status.value(), message)
        }

        fun ok(message: String): BaseResponse {
            return BaseResponse(HttpStatus.OK.value(), message)
        }

        fun created(message: String): BaseResponse {
            return BaseResponse(HttpStatus.CREATED.value(), message)
        }

        fun noContent(message: String): BaseResponse {
            return BaseResponse(HttpStatus.NO_CONTENT.value(), message)
        }
    }
}