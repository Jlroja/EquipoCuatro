package com.example.miniproyecto1.model

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthModel @Inject constructor(private val auth: FirebaseAuth) {

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Login exitoso")
                } else {
                    callback(false, "Login incorrecto: ${task.exception?.message ?: "Error desconocido"}")
                }
            }
    }

    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Registro exitoso")
                } else {
                    callback(false, "Error en el registro: ${task.exception?.message ?: "Error desconocido"}")
                }
            }
    }
}

