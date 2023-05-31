package com.davito.valorantapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.davito.valorantapp.local.model.FavoriteAgent


@Dao
interface AgentDao {

    @Insert
    suspend fun saveAgent(agent: FavoriteAgent)

    @Query("DELETE FROM favorite_agents WHERE id LIKE :id")
    suspend fun deleteAgent(id: String)

    @Query("SELECT * FROM favorite_agents WHERE id LIKE :id")
    suspend fun searchAgent(id: String): FavoriteAgent

    @Query("SELECT * FROM favorite_agents")
    suspend fun loadAgents(): MutableList<FavoriteAgent>
}