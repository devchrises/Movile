package com.pllp.android.movile.data.repository

import com.pllp.android.movile.data.source.local.CredentialDataStore
import com.pllp.android.movile.domain.model.Credential
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CredentialRepositoryIntegrationTest {
    private lateinit var credentialDataStore: CredentialDataStore
    private lateinit var repositoryImpl: CredentialRepositoryImpl

    @Before
    fun setup() {
        credentialDataStore = CredentialDataStore()
        repositoryImpl = CredentialRepositoryImpl(credentialDataStore)
    }

    @Test
    fun `login succeds with valid credentials`() = runTest {
        val validCredential = Credential("Admin", "Password*123")

        val result = repositoryImpl.login(validCredential)

        assertTrue(result.isSuccess)

        assertEquals("Login exitoso", result.getOrNull())
    }

    @Test
    fun `login fails with invalid credentials`() = runTest {
        val invalidCredential = Credential("edmen", "*123")

        val result = repositoryImpl.login(invalidCredential)

        assertTrue(result.isFailure)

        assertEquals("Credenciales inválidas", result.exceptionOrNull()?.message)
    }

    @Test
    fun `login fails with correct user but wrong password`() = runTest {
        val credentials = Credential("Admin", "ppssword*123")

        val result = repositoryImpl.login(credentials)

        assertTrue(result.isFailure)
        assertEquals("Credenciales inválidas", result.exceptionOrNull()?.message)
    }
}