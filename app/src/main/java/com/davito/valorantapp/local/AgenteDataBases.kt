package com.davito.valorantapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davito.valorantapp.local.model.FavoriteAgent

@Database(entities = [FavoriteAgent::class], version = 1)
abstract class AgenteDataBases : RoomDatabase() {

    abstract fun AgentDao() : AgentDao
}