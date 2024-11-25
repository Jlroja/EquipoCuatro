package com.example.miniproyecto1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.miniproyecto1.repository.LoginRepository

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    // Llamada para iniciar sesión
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        loginRepository.login(email, password, callback)
    }

    // Llamada para registrar un nuevo usuario
    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        loginRepository.register(email, password, callback)
    }
}

