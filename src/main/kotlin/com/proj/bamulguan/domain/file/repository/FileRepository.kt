package com.proj.bamulguan.domain.file.repository

import com.proj.bamulguan.domain.file.entity.FileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<FileEntity, Long> {
}