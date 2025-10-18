package com.pllp.android.movile.domain.interfaces

import com.pllp.android.movile.domain.model.Credential

interface AuthRepository {
    suspend fun login(credential: Credential): Result<String>
}