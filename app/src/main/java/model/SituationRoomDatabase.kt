package model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import junit.runner.Version
import java.security.AccessControlContext
@Database(entities = [Situation::class],version = 1)
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