package com.example.miniproyecto1.data

import android.content.Context
import com.example.miniproyecto1.model.Challenge
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ChallengeDBFirebase @Inject constructor(
    private val context: Context

) {
    private val db = FirebaseFirestore.getInstance()
    private val challengeCollection = db.collection("challenges")

    suspend fun saveChallenge(challenge: Challenge): String? {
        return try {
            val documentRef = challengeCollection.add(challenge).await()
            challenge.id = documentRef.id
            print(documentRef)
            challengeCollection.document(documentRef.id).set(challenge).await()
            documentRef.id


        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getListChallenge(): MutableList<Challenge> {
        return try {
            val querySnapshot = challengeCollection.get().await()
            querySnapshot.documents.mapNotNull { document ->
                document.toObject<Challenge>()
            }.toMutableList()
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    suspend fun deleteChallenge(documentId: String): Boolean {
        if (documentId.isEmpty()) {
            println("Error: El documentId no puede estar vacío.")
            return false
        }
        return try {
            challengeCollection.document(documentId).delete().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun updateChallenge(challenge: Challenge): Boolean {
        return try {
            print("updateChallenge firebase"+challenge.id)
            challengeCollection.document(challenge.id).set(challenge).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}