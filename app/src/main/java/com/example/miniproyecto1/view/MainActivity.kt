package com.example.miniproyecto1.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.miniproyecto1.R
import com.example.miniproyecto1.repository.LoginRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Hilt inyectará las dependencias necesarias
    @Inject lateinit var loginRepository: LoginRepository  // Inyectamos LoginRepository
    private lateinit var db: FirebaseFirestore  // Si quieres usar Firestore con Hilt, también puedes inyectarlo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Si lo deseas, puedes inyectar FirebaseFirestore con Hilt, pero si lo haces manualmente, no es necesario.
        db = FirebaseFirestore.getInstance()  // O usa inyección si tienes un módulo para Firebase

        setContentView(R.layout.activity_main)

        // Ahora puedes usar loginRepository
        // loginRepository.login(email, password, callback) en donde lo necesites
    }
}
