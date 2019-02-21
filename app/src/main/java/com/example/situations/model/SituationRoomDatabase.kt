package com.example.situations.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database( entities = [Situation::class],version = 1, exportSchema = false)
abstract  class SituationRoomDatabase : RoomDatabase() {

    abstract fun situationDao() : SituationDAO
    companion object {
        @Volatile
        var database: SituationRoomDatabase?=null
        fun getInstance(context: Context): SituationRoomDatabase?{

         if(database==null)
         {
             synchronized(SituationRoomDatabase::class.java){
                if(database==null){

                    database= Room.databaseBuilder(context.applicationContext,SituationRoomDatabase::class.java
                        ,"situations_database").build()

                }

             }

         }
        return database
        }
    }
}