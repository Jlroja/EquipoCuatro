package com.example.miniproyecto1.repository

import android.content.Context
import com.example.miniproyecto1.data.ChallengeDBFirebase
import com.example.miniproyecto1.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject // Asegúrate de importar esta anotación

class ChallengeRepository @Inject constructor(
    val context: Context // Asegúrate de tener la anotación aquí
) {
    private val challengeDao: ChallengeDBFirebase = ChallengeDBFirebase(context)

    suspend fun saveChallenge(challenge: Challenge) {
        withContext(Dispatchers.IO) {
            challengeDao.saveChallenge(challenge)
        }
    }

    suspend fun updateChallenge(challenge: Challenge) {
        withContext(Dispatchers.IO) {
            print("updateChallenge repository "+challenge.id)
            challengeDao.updateChallenge(challenge)
        }
    }

    suspend fun deleteChallenge(challenge: Challenge) {
        withContext(Dispatchers.IO) {
            challengeDao.deleteChallenge(challenge.id)
        }
    }

    suspend fun getListChallenge(): MutableList<Challenge> {
        return withContext(Dispatchers.IO) {
            challengeDao.getListChallenge()
        }
    }
}
