package com.example.situations.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.situations.model.Situation
import com.example.situations.model.SituationRepository

class SituationViewModel (application: Application) : AndroidViewModel(application) {
    private val situationRepository: SituationRepository
    private val allSituation: LiveData<List<Situation>>
    init {
        situationRepository = SituationRepository(application)
        allSituation = situationRepository.getAllSituation()
    }
    fun insertSituation(situation: Situation)
    {
        situationRepository.insertSituation(situation)
    }
    fun deleteSituation(situation: Situation)
    {
        situationRepository.deleteSituation(situation)
    }
    fun deleteAllSituation()
    {
        situationRepository.deleteAllSituation()
    }
    fun getAllSituation() =  allSituation

    fun getSituationByName (name: String): Situation?
    {
        return situationRepository.getSituationByName(name)
    }





}