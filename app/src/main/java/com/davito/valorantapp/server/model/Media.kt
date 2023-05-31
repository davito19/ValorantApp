package com.davito.valorantapp.server.model


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id")
    val id: Int,
    @SerializedName("wave")
    val wave: String,
    @SerializedName("wwise")
    val wwise: String
)