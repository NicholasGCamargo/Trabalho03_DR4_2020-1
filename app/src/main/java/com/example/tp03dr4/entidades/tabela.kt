package com.example.tp03dr4.entidades

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.tp03dr4.classes.Converters

@Entity(primaryKeys = arrayOf("id_avaliado", "nome_empresa", "bairro"))
@TypeConverters(Converters::class)
class tabela(
    val id_avaliado: String,
    val nome_empresa: String,
    val bairro: String,
    val resp1: Float,
    val resp2: Float,
    val resp3: Float,
    val resp4: Float,
    val resp5: Float,
    val resp6:Float
) {
}