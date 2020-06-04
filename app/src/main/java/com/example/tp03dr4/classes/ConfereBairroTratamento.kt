package com.example.tp03dr4.classes

import com.example.tp03dr4.entidades.BairroTabela

class ConfereBairroTratamento(
    val bairros: MutableList<String> = mutableListOf(),
    val qtd: MutableList<Int> = mutableListOf(),
    val resultado: MutableList<BairroTabela> = mutableListOf()
) {
}