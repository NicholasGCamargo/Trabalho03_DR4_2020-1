package com.example.tp03dr4.dao

import androidx.room.*
import com.example.tp03dr4.entidades.BairroTabela
import com.example.tp03dr4.entidades.tabela

@Dao
interface tabelaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tabela: tabela)
    @Update
    fun update(tabela: tabela)
    @Delete
    fun delete(tabela: tabela)

    //Pensando ainda em como descriptografar os dados antes de por na tabela
    @Query("SELECT * FROM tabela WHERE id_avaliado = :idPassado")
    fun all(idPassado: String): Array<tabela>

    @Query("SELECT AVG(resp1) as avg1, AVG(resp2) as avg2, AVG(resp3) as avg3, AVG(resp4) as avg4, AVG(resp5) as avg5, AVG(resp6) as  avg6, bairro FROM tabela ORDER BY bairro")
    fun bairro(): Array<BairroTabela>
}