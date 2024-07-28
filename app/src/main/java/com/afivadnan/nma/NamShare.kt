@file:Suppress("DEPRECATION")

package com.afivadnan.nma

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class NamShare(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val pref = EncryptedSharedPreferences.create(
        PREF_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveNama(value: String) {
        val editor = pref.edit()
        editor.putString(KEY_NAME, value)
        editor.apply()
    }

    fun dpNama(): String? {
        return pref.getString(KEY_NAME, "-")
    }

    companion object {
        const val PREF_NAME = "pref_nama"
        const val KEY_NAME = "Nama"
    }
}
