package com.example.miniproyecto1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.miniproyecto1.model.Challenge

@Dao
interface ChallengeDaoFirebase {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenge(challenge: Challenge)

    @Query("SELECT * FROM Challenge order by id DESC")
    suspend fun getListChallenge(): MutableList<Challenge>

    @Delete
    suspend fun deleteChallenge(challenge: Challenge)

    @Update
    suspend fun updateChallenge(challenge: Challenge)
}