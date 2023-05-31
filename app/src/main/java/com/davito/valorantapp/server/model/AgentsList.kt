package com.davito.valorantapp.server.model


import com.google.gson.annotations.SerializedName

data class AgentsList(
    @SerializedName("data")
    val agents: List<Agent>,
    @SerializedName("status")
    val status: Int
)