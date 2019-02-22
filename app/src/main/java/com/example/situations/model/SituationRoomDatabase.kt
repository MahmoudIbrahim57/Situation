package com.example.situations.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database( entities = [Situation::class],version = 1)
abstract  class SituationRoomDatabase : RoomDatabase() {
    abstract fun situationDao(): SituationDAO

     companion object {
        @Volatile
        var dbInstance: SituationRoomDatabase? = null

        var dbName = "situations_database"

        fun getInstance(context: Context): SituationRoomDatabase {
            val tempInstance = dbInstance
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, SituationRoomDatabase::class.java,dbName).build()
                dbInstance = instance
                return instance
            }

        }
    }
}