package com.example.situations.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface SituationDAO {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun insertSituation(situation: Situation)

    @Delete
    fun deleteSituation(situation: Situation)

    @Query("DELETE FROM situation_table")
    fun deleteAllSituation()

    @Query("SELECT * FROM situation_table ORDER BY name ASC ")
    fun getAllSituation(): LiveData<List<Situation>>

    @Query("SELECT * FROM situation_table WHERE name= :name ")
    fun getSituationByName (name: String): Situation



}