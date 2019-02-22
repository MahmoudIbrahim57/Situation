package com.example.situations.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "situation_table")

data class Situation(@PrimaryKey @NonNull val name: String,@NonNull val meaning: String )
{



}