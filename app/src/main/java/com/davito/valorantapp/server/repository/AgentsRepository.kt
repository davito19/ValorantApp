package com.davito.valorantapp.server.repository

import com.davito.valorantapp.server.AgentsDB

class AgentsRepository {


    suspend fun loadAgents() = AgentsDB.retrofit.loadMovies()
}