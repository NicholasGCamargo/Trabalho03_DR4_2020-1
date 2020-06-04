package com.example.tp03dr4.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BairroTabela(
    val avg1: Float,
    val avg2: Float,
    val avg3: Float,
    val avg4: Float,
    val avg5: Float,
    val avg6: Float,
    @PrimaryKey(autoGenerate = false)
    val bairro: String
) {
}