package com.example.miniproyecto1.data

import android.content.Context
import com.example.miniproyecto1.model.Challenge
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await


class ChallengeDBFirebase(private val context: Context)  {
    private val db = FirebaseFirestore.getInstance()
    private val challengeCollection = db.collection("challenges")

    //fun getChallengeCollection() = challengeCollection
    suspend fun saveChallenge(challenge: Challenge): String? {
        println("save")
        return try {
            val documentRef = challengeCollection.add(challenge).await()
            println(documentRef.id)
            documentRef.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getListChallenge(): MutableList<Challenge> {
        return try {
            val querySnapshot = this.challengeCollection.get().await()
            querySnapshot.documents.mapNotNull { document ->
                document.toObject<Challenge>()
            }.toMutableList()
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    suspend fun deleteChallenge(documentId: String): Boolean {
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
            challengeCollection.document(challenge.id).set(challenge).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
