package com.davito.valorantapp.server

import com.davito.valorantapp.server.model.AgentsList
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    suspend fun loadMovies() : AgentsList

}