package com.example.miniproyecto1.model


import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Challenge(
    var id: String = "",
    var description: String = "",
) : Serializable