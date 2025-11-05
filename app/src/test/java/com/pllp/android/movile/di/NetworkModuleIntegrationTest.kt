package com.pllp.android.movile.di

import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Call
import retrofit2.http.GET
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.DefaultAsserter.fail

class NetworkModuleIntegrationTest {

    interface TestApi {
        @GET("movie/550") // endpoint simple público (Fight Club)
        fun ping(): Call<ResponseBody>
    }

    @Test
    fun `retrofit provideRetrofit should connect and return success`() {
        try {
            val retrofit = NetworkModule.provideRetrofit()
            val api = retrofit.create(TestApi::class.java)
            val response = api.ping().execute()

            // Aserción minimalista: la petición fue exitosa (2xx)
            assertTrue("La petición fue exitosa. Código: ${response.code()}", response.isSuccessful)
        } catch (e: Exception) {
            // Fallar el test si hay problema de IO, DNS, etc.
            fail("Error ejecutando la petición de integración: ${e.message}")
        }
    }
}