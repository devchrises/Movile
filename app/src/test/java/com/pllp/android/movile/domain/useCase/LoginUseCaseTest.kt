package com.pllp.android.movile.domain.useCase

import com.pllp.android.movile.domain.fakes.FakeAuthRepositoryFailure
import com.pllp.android.movile.domain.fakes.FakeAuthRepositorySuccess
import com.pllp.android.movile.domain.model.Credential
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginUseCaseTest {
    @Test
    fun `invoke returns success when repository succeeds`() = runTest {
        val repository = FakeAuthRepositorySuccess()
        val useCase = LoginUseCase(authRepository = repository)
        val credential = Credential(user = "user", password = "password")

        val result = useCase(credential = credential)

        assertTrue(result.isSuccess)
        assertEquals("Login successful for user", result.getOrNull())
    }

    @Test
    fun `invoke returns failure when repository fails`() = runTest {
        val repository = FakeAuthRepositoryFailure()
        val useCase = LoginUseCase(authRepository = repository)
        val credential = Credential(user = "user", password = "password")

        val result = useCase(credential = credential)

        assertTrue(result.isFailure)
        assertEquals("Invalid credentials", result.exceptionOrNull()?.message)
    }
}