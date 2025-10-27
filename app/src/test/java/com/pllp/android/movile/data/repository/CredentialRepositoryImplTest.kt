package com.pllp.android.movile.data.repository

import com.pllp.android.movile.data.model.DataCredential
import com.pllp.android.movile.data.source.local.CredentialDataStore
import com.pllp.android.movile.domain.model.Credential
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CredentialRepositoryImplTest {

    //Parámetro del constructor
    @MockK
    lateinit var credentialDataStore: CredentialDataStore

    //Clase a testear
    private lateinit var repository: CredentialRepositoryImpl

    @Before
    fun setup() {
        // Inicializa los mocks anotados con @MockK
        MockKAnnotations.init(this)

        repository = CredentialRepositoryImpl(credentialDataStore)
    }

    @Test
    fun `login devuelve éxito cuando las credenciales son válidas`() = runTest {
        val validCredential = Credential("Admin", "Password*123")
        val storedCredentials = listOf(
            DataCredential("Admin", "Password*123"),
        )

        // Configuramos el comportamiento del mock:
        // cuando se llame a getAuthorizedAccount(), devolverá nuestra lista
        coEvery { credentialDataStore.getAuthorizedAccount() } returns storedCredentials

        // when: intentamos hacer login con las credenciales correctas
        val result = repository.login(validCredential)

        // then: esperamos que sea exitoso
        assertTrue(result.isSuccess)
        assertEquals("Login exitoso", result.getOrNull())

        // Y verificamos que el método haya sido invocado una sola vez
        coVerify(exactly = 1) { credentialDataStore.getAuthorizedAccount() }
    }

    @Test
    fun `login devuelve error cuando las credenciales son inválidas`() = runTest {
        val invalidCredential = Credential("admin", "password*456")
        val storedCredentials = listOf(
            DataCredential("Admin", "Password*123"),
        )

        // Configuramos el comportamiento del mock:
        // cuando se llame a getAuthorizedAccount(), devolverá nuestra lista
        coEvery { credentialDataStore.getAuthorizedAccount() } returns storedCredentials

        // when: intentamos hacer login con las credenciales correctas
        val result = repository.login(invalidCredential)

        // then: esperamos que sea exitoso
        assertTrue(result.isFailure)
        assertEquals("Credenciales inválidas", result.exceptionOrNull()?.message)

        // Y verificamos que el método haya sido invocado una sola vez
        coVerify(exactly = 1) { credentialDataStore.getAuthorizedAccount() }
    }
}