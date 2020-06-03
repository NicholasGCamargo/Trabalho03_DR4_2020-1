package com.example.tp03dr4.entidades

import androidx.room.Entity

@Entity(primaryKeys = arrayOf("id_avaliado", "nome_empresa"))
class tabela(
    val id_avaliado: String,
    val nome_empresa: ByteArray,
    val bairro: ByteArray,
    val resp1: Float,
    val resp2: Float,
    val resp3: Float,
    val resp4: Float,
    val resp5: Float,
    val resp6:Float
) {
}