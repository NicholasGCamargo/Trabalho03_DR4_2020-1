package com.example.tp03dr4.BD

import android.content.Context
import androidx.room.Room

class MyDatabaseService {
    companion object {
        var appdataBase: MyDatabase? = null
        val database_name = "appdatabase.db"
        fun getInstance(context: Context): MyDatabase {
            if (appdataBase == null) {
                appdataBase = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    database_name
                )
                    .build()
            }
            return appdataBase as MyDatabase
        }
    }
}

