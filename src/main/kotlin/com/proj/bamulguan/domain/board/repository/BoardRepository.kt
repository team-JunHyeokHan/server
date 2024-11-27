package com.proj.bamulguan.domain.board.repository

import com.proj.bamulguan.domain.board.entity.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<BoardEntity, Long>{

}