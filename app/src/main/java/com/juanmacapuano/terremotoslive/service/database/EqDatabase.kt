package com.juanmacapuano.terremotoslive.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanmacapuano.terremotoslive.service.data.EarthQuake

@Database(entities = [EarthQuake::class], version = 1)
abstract class EqDatabase: RoomDatabase() {
    abstract val eqDao : EqDao
}

private lateinit var INSTANCE: EqDatabase

fun getDatabase(context: Context): EqDatabase {
    synchronized(EqDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = androidx.room.Room.databaseBuilder(context.applicationContext, com.juanmacapuano.terremotoslive.service.database.EqDatabase::class.java,
            "earthquakesdb").build()
        }
        return INSTANCE
    }
}