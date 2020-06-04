package com.example.tp03dr4.classes

import android.util.Log
import com.example.tp03dr4.entidades.Bairro

class TratamentoBairros(val dados: Array<Bairro>) {
    fun tratarDados(): List<CriptoString>{
        var retornar = mutableListOf<CriptoString>()
        dados.forEach {
            retornar.add(it.bairro)
            Log.d("ADIOCIONANDO BAIRRO", it.bairro.getClearText())
        }

        return retornar
    }
}