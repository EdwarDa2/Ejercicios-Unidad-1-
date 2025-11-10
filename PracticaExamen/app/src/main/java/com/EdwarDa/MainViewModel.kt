package com.EdwarDa

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.EdwarDa.data.AppDatabase
import com.EdwarDa.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getInstance(application).userDao()

    var name by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var age by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }
    fun onLastNameChange(newLastName: String) {
        lastName = newLastName
    }
    fun onAgeChange(newAge: String) {
        age = newAge
    }

    fun saveData() {
        if (name.isBlank() || lastName.isBlank() || age.isBlank()) {
            resultState = "Por favor, llena todos los campos"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val newUser = User(
                name = name,
                lastName = lastName,
                age = age
            )

            userDao.insertUser(newUser)

            withContext(Dispatchers.Main) {
                name = ""
                lastName = ""
                age = ""
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            resultState = "Guardando y enviando..."
            val result = withContext(Dispatchers.IO) {
                delay(1000)
                "¡Usuario añadido con éxito!"
            }
            resultState = result
        }
    }

    var resultState by mutableStateOf(" ")
}