package com.pllp.android.movile.domain.useCase

import com.pllp.android.movile.domain.model.Credential
import com.pllp.android.movile.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(credential: Credential): Result<String> {
        return authRepository.login(credential)
    }
}