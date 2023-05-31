package com.davito.valorantapp

import android.app.Application
import androidx.room.Room
import com.davito.valorantapp.local.AgenteDataBases

class ValorantApp : Application() {

    companion object{
        lateinit var database : AgenteDataBases
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AgenteDataBases::class.java,
            "agents_db"
        ).build()
    }
}