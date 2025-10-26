package com.pllp.android.movile.data.source.local

import com.pllp.android.movile.data.model.DataCredential
import javax.inject.Inject

class CredentialDataStore @Inject constructor() {
    fun getAuthorizedAccount(): List<DataCredential> {
        val user1 = DataCredential(user = "Admin", password = "Password*123")
        return listOf(user1)
    }
}