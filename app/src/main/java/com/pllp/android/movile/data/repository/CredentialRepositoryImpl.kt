package com.pllp.android.movile.data.repository

import com.pllp.android.movile.data.source.local.CredentialDataStore
import com.pllp.android.movile.domain.model.Credential
import com.pllp.android.movile.domain.repository.AuthRepository
import javax.inject.Inject

class CredentialRepositoryImpl @Inject constructor(private val credentialDataStore: CredentialDataStore) :
    AuthRepository {
    override suspend fun login(credential: Credential): Result<String> {
        val isAuthorized = credentialDataStore.getAuthorizedAccount()
            .any() { it.user == credential.user && it.password == credential.password }
        return if (isAuthorized) {
            Result.success("Login exitoso")
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }
}