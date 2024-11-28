package com.example.miniproyecto1.repository

import com.example.miniproyecto1.model.AuthModel
import javax.inject.Inject

class LoginRepository @Inject constructor( // Hilt inyectará la dependencia de AuthModel
    private val authModel: AuthModel  // Inyectamos AuthModel en lugar de crear una instancia manualmente
) {

    // Función para iniciar sesión
    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authModel.login(email, password, callback)
    }

    // Función para registrar un nuevo usuario
    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authModel.register(email, password, callback)
    }
}


