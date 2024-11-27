package com.example.miniproyecto1.model


import com.google.firebase.firestore.PropertyName
import java.io.Serializable

class Challenge(
    @get:PropertyName("id")
    val id: String = "",

    @get:PropertyName("description") @set:PropertyName("description")
    var description: String = ""
) : Serializable