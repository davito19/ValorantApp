package com.davito.valorantapp.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_agents")
data class FavoriteAgent(
    @PrimaryKey @ColumnInfo(name = "id") val id : String,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "developer_name") val developerName : String?,
    @ColumnInfo(name = "description") val description : String?,
    @ColumnInfo(name = "role") val role : String?,
    @ColumnInfo(name = "role_description") val roleDescription : String?,
    @ColumnInfo(name = "url_image") val urlImage : String?
) : java.io.Serializable
