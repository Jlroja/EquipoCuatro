package com.example.miniproyecto1.model

import com.google.firebase.auth.FirebaseAuth

class AuthModel {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Login exitoso")
                } else {
                    callback(false, "Login incorrecto: ${task.exception?.message}")
                }
            }
    }


    fun register(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Registro exitoso")
                } else {
                    callback(false, "Error en el registro: ${task.exception?.message}")
                }
            }
    }
}
