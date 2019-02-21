package com.example.situations.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class SituationRepository(application: Application) {

    private val situationDAO:SituationDAO?
    private val allSituation: LiveData<List<Situation>>?
    init {
        val db= SituationRoomDatabase.getInstance(application)
        situationDAO=db?.situationDao()
        allSituation=situationDAO?.getAllSituation()
    }
    fun insertSituation(situation: Situation)
    {
        InsertAsynTask(situationDAO!!).execute(situation)
    }



    fun deleteSituation(situation: Situation)
    {
        DeleteAsynTask(situationDAO!!).execute(situation)
    }
    fun deleteAllSituation()
    {
        DeleteAllAsynTask(situationDAO!!).execute()
    }
    fun getAllSituation(): LiveData<List<Situation>>
    {
        DeleteAllAsynTask(situationDAO!!).execute()
        return allSituation!!
    }
    fun getSituationByName (name: String): Situation?
    {
        val allSituationList = allSituation?.value?.toList()
        allSituationList?.iterator()?.forEach {
            if(it.name==name){
                return it

            }
        }
        return null
    }
    private class InsertAsynTask(private val situationDAO: SituationDAO):AsyncTask<Situation,Void,Void>() {
        override fun doInBackground(vararg params: Situation): Void? {
            situationDAO.insertSituation(params[0])
            return null
        }
    }
    private class DeleteAsynTask(private val situationDAO: SituationDAO):AsyncTask<Situation,Void,Void>() {
        override fun doInBackground(vararg params: Situation): Void? {
            situationDAO.deleteSituation(params[0])
            return null
        }
    }
    private class DeleteAllAsynTask(private val situationDAO: SituationDAO):AsyncTask<Void,Void,Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            situationDAO.deleteAllSituation()
            return null
        }


    }

}