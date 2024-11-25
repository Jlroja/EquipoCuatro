package com.example.miniproyecto1.repository

import com.example.miniproyecto1.model.AuthModel

class LoginRepository {

    private val authModel = AuthModel()

    // Función para iniciar sesión
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authModel.login(email, password, callback)
    }

    // Función para registrar un nuevo usuario
    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authModel.register(email, password, callback)
    }
}
