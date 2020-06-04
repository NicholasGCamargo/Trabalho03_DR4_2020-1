package com.example.tp03dr4.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tp03dr4.classes.CriptoString

@Entity
class BairroTabela(
    var avg1: Float,
    var avg2: Float,
    var avg3: Float,
    var avg4: Float,
    var avg5: Float,
    var avg6: Float,
    @PrimaryKey(autoGenerate = false)
    val bairro: CriptoString
) {
}