package com.example.tp03dr4.dao

import androidx.room.*
import com.example.tp03dr4.classes.CriptoString
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

    @Query("SELECT AVG(resp1) as avg1, AVG(resp2) as avg2, AVG(resp3) as avg3, AVG(resp4) as avg4, AVG(resp5) as avg5, AVG(resp6) as  avg6, bairro FROM TabelaPrincipal WHERE bairro = :bairro")
    fun bairro(bairro: CriptoString): Array<BairroTabela>

    //vamos tentar um tipo de recursao de banco de dados, onde pegamos o AVG de cada bairro apos pegar cada bairro
    @Query("SELECT bairro from TabelaPrincipal")
    fun allBairro():Array<BairroSolo>
}