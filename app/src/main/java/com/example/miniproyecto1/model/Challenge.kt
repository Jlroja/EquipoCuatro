package com.example.miniproyecto1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

@Entity
data class Challenge(
    @PrimaryKey
    @get:PropertyName("id")
    val id: String = "",

    @get:PropertyName("description") @set:PropertyName("description")
    var description: String = ""
) : Serializable