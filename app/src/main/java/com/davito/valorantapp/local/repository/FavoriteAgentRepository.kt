package com.davito.valorantapp.local.repository

import com.davito.valorantapp.ValorantApp
import com.davito.valorantapp.local.AgentDao
import com.davito.valorantapp.local.model.FavoriteAgent

class FavoriteAgentRepository {

    suspend fun searchAgent(uuid: String): FavoriteAgent {
        val agentDao : AgentDao = ValorantApp.database.AgentDao()
        return agentDao.searchAgent(uuid)
    }

    suspend fun saveAgent(agent: FavoriteAgent) {
        val agentDao : AgentDao = ValorantApp.database.AgentDao()
        agentDao.saveAgent(agent)
    }

    suspend fun loadAgents() : MutableList<FavoriteAgent> {
        val agentDao : AgentDao = ValorantApp.database.AgentDao()
        return agentDao.loadAgents()
    }

    suspend fun deleteAgets(id : String) {
        val agentDao : AgentDao = ValorantApp.database.AgentDao()
        agentDao.deleteAgent(id)
    }
}