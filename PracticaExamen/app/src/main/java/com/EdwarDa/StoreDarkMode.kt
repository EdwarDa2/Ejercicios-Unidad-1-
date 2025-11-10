package com.EdwarDa

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DarkMode")
class StoreDarkMode(private val context: Context) {
    companion object {
        val DARKMODE = booleanPreferencesKey("darkMode")
    }

    suspend fun saveDarkMode(value: Boolean) {
        context.dataStore.edit { preference ->
            preference[DARKMODE] = value
        }
    }

    val getDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preference ->
            preference[DARKMODE] ?: false

        }

}