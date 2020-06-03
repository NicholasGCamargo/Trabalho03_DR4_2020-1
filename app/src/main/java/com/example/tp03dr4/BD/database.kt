package com.example.tp03dr4.BD

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tp03dr4.classes.Converters
import com.example.tp03dr4.dao.tabelaDAO
import com.example.tp03dr4.entidades.tabela

@Database(
    entities = arrayOf(tabela::class),
    version = 1
)
@TypeConverters(Converters::class)
abstract class database(): RoomDatabase() {
    abstract fun tabelaDAO(): tabelaDAO
}