package com.proj.bamulguan

import com.proj.bamulguan.domain.user.UserEntity
import com.proj.bamulguan.domain.user.UserRepository
import com.proj.bamulguan.domain.user.UserService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTest {

    private val userRepository: UserRepository = mock()
    private val userService = UserService(userRepository)

    @Test
    fun `addToken should save user with fcmToken`() {
        val token = "sample_token"

        // mock userRepository.save 메서드 호출에 대한 동작 설정
        whenever(userRepository.save(any())).thenReturn(UserEntity(id = 1, fcmToken = token))

        // 메서드 실행
        userService.addToken(token)

        // userRepository.save가 정확하게 호출되었는지 검증
        verify(userRepository).save(
            UserEntity(
                id = null,  // id는 null로, 새로 생성된 엔티티임
                fcmToken = token
            )
        )
    }
}