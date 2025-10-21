package com.pllp.android.movile.domain.fakes

import com.pllp.android.movile.domain.model.Credential
import com.pllp.android.movile.domain.repository.AuthRepository

class FakeAuthRepositorySuccess : AuthRepository {
    override suspend fun login(credential: Credential): Result<String> {
        return Result.success("Login successful for ${credential.user}")
    }
}

class FakeAuthRepositoryFailure : AuthRepository {
    override suspend fun login(credential: Credential): Result<String> {
        return Result.failure(Exception("Invalid credentials"))
    }
}