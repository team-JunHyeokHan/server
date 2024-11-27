package com.proj.bamulguan.domain.file.service

import com.proj.bamulguan.domain.file.dto.FileDto
import com.proj.bamulguan.domain.file.entity.FileEntity
import com.proj.bamulguan.domain.file.repository.FileRepository
import com.proj.bamulguan.global.s3.S3Service
import org.springframework.stereotype.Service

@Service
class FileService(
    private val fileRepository: FileRepository,
    private val s3Service: S3Service
) {
    fun save(fileUrl: String): FileDto{
        val fileEntity = fileRepository.save(
            FileEntity(
                id = null,
                url = fileUrl
            )
        )
        return FileDto(
            id = fileEntity.id,
            url = fileEntity.url
        )
    }
}