package com.example.miniproyecto1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.utils.Constants

@Database(entities = [Challenge::class], version = 1)
abstract class ChallengeDB : RoomDatabase(){

    abstract fun challengeDao() : ChallengeDao

    companion object{
        fun getDatabase(context: Context): ChallengeDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ChallengeDB::class.java,
                Constants.NAME_BD
            ).build()
        }
    }
}
