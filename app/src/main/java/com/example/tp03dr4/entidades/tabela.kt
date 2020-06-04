package com.example.tp03dr4.entidades

import androidx.room.Entity
import com.example.tp03dr4.classes.CriptoString

@Entity(primaryKeys = arrayOf("id_avaliado", "nome_empresa", "bairro"))
class tabela(
    val id_avaliado: String,
    val nome_empresa: CriptoString,
    val bairro: CriptoString,
    val resp1: Float,
    val resp2: Float,
    val resp3: Float,
    val resp4: Float,
    val resp5: Float,
    val resp6:Float
) {
}