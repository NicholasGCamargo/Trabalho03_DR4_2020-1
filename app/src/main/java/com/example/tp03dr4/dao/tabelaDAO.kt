package com.example.tp03dr4.dao

import androidx.room.*
import com.example.tp03dr4.entidades.BairroSolo
import com.example.tp03dr4.entidades.BairroTabela
import com.example.tp03dr4.entidades.TabelaPrincipal

@Dao
interface tabelaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(TabelaPrincipal: TabelaPrincipal)
    @Update
    fun update(TabelaPrincipal: TabelaPrincipal)
    @Delete
    fun delete(TabelaPrincipal: TabelaPrincipal)

    //Pensando ainda em como descriptografar os dados antes de por na tabela
    @Query("SELECT * FROM TabelaPrincipal WHERE id_avaliado = :idPassado")
    fun all(idPassado: String): Array<TabelaPrincipal>

    @Query("SELECT resp1 as avg1, resp2 as avg2, resp3 as avg3, resp4 as avg4, resp5 as avg5, resp6 as  avg6, bairro FROM TabelaPrincipal")
    fun bairro(): Array<BairroTabela>

    //
    //Quando tento selecionar os bairros algo de errado e retornar somente uma linha, então vamos
    //pegar os bairros separadamente antes
    //

    @Query("SELECT bairro from TabelaPrincipal")
    fun allBairro():Array<BairroSolo>
}