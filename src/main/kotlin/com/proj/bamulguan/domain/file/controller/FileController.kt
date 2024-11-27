package com.proj.bamulguan.domain.file.controller

import com.proj.bamulguan.domain.file.dto.FileDto
import com.proj.bamulguan.domain.file.service.FileService
import com.proj.bamulguan.global.response.BaseResponseData
import com.proj.bamulguan.global.s3.S3Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/file")
class FileController(
    private val s3Service: S3Service,
    private val fileService: FileService
) {

    @PostMapping("/file")
    fun upload(@RequestPart files: List<MultipartFile>): BaseResponseData<List<FileDto>> {
        val boards: MutableList<FileDto> = ArrayList()
        for (multipartFile in files) {
            val file = s3Service.upload(multipartFile, "밤물관")
            boards.add(fileService.save(file))
        }
        return BaseResponseData.ok("성공", boards)
    }

}