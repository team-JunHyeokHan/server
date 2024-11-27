package com.proj.bamulguan.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.proj.bamulguan.global.s3.config.S3Config
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class S3Service (
    private val amazonS3: AmazonS3,
    private val s3Config: S3Config
){

    fun upload(multipartFile: MultipartFile, dirName: String): String {
        val originFileName: String? = multipartFile.originalFilename;

        val uuid = UUID.randomUUID().toString()
        requireNotNull(originFileName) { "Original file name must not be null" }
        val uniqueFileName = "$uuid${originFileName.replace("\\s".toRegex(), "_")}"
        val fileName = "$dirName/$uniqueFileName"

        val objectMetadata = ObjectMetadata().apply {
            contentLength = multipartFile.size
            contentType = multipartFile.contentType
        }

        try {
            multipartFile.inputStream.use { inputStream ->
                amazonS3.putObject(
                    PutObjectRequest(s3Config.bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                )
            }
        } catch (e: IOException) {
            throw IllegalArgumentException("파일을 올리는데 실패했습니다. $fileName", e)
        }

        val url = amazonS3.getUrl(s3Config.bucket, fileName).toString()
        return url
    }
}