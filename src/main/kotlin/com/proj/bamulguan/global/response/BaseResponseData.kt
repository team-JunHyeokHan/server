package com.proj.bamulguan.global.response

import org.springframework.http.HttpStatus

data class BaseResponseData<T>(
    val status: Int,
    val message: String,
    val data: T
) {

    companion object {
        fun <T> of(status: HttpStatus, message: String, data: T): BaseResponseData<T> {
            return BaseResponseData(status.value(), message, data)
        }

        fun <T> ok(message: String, data: T): BaseResponseData<T> {
            return BaseResponseData(HttpStatus.OK.value(), message, data)
        }

        fun <T> created(message: String, data: T): BaseResponseData<T> {
            return BaseResponseData(HttpStatus.CREATED.value(), message, data)
        }
    }
}