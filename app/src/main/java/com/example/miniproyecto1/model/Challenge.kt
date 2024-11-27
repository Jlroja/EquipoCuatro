package com.example.miniproyecto1.model


import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Challenge(
    var id: String = "",  // Asegúrate de que el id esté presente y sea mutable
    var description: String = "",  // Otros campos del reto
) : Serializable