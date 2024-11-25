package com.example.miniproyecto1.repository

import android.content.Context
import com.example.miniproyecto1.data.ChallengeDB
import com.example.miniproyecto1.data.ChallengeDBFirebase
import com.example.miniproyecto1.data.ChallengeDao
import com.example.miniproyecto1.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository(val context: Context) {
    private val challengeDao: ChallengeDBFirebase = ChallengeDBFirebase(context)

    suspend fun saveChallenge(challenge:Challenge){
        withContext(Dispatchers.IO){
            challengeDao.saveChallenge(challenge)
        }
    }

    suspend fun updateChallenge(challenge:Challenge){
        withContext(Dispatchers.IO){
            challengeDao.updateChallenge(challenge)
        }
    }

    suspend fun deleteChallenge(challenge:Challenge){
        withContext(Dispatchers.IO){
            challengeDao.deleteChallenge(challenge.id)
        }
    }



    suspend fun getListChallenge():MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            challengeDao.getListChallenge()
        }
    }
}