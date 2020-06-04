package com.example.tp03dr4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp03dr4.R
import com.example.tp03dr4.entidades.TabelaPrincipal
import kotlinx.android.synthetic.main.rcy_layout.view.*

class AdapterListagemUsuario(val lista: Array<TabelaPrincipal>): RecyclerView.Adapter<AdapterListagemUsuario.DadosViewHolder>() {

    class DadosViewHolder(v: View): RecyclerView.ViewHolder(v){
        val campoRest = v.nomeRest
        val campoBairro = v.bairroRest
        val perg1 = v.perg1SimNao
        val perg2 = v.perg2SimNao
        val perg3 = v.perg3SimNao
        val perg4 = v.perg4SimNao
        val perg5 = v.perg5SimNao
        val perg6 = v.perg6SimNao
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: DadosViewHolder, position: Int) {
        val dado = lista[position]
        holder.campoBairro.text = dado.bairro.getClearText()
        holder.campoRest.text = dado.nome_empresa.getClearText()
        holder.perg1.text = "Resp 1: ${if(dado.resp1 == 1f) "sim" else "não"}"
        holder.perg2.text = "Resp 2: ${if(dado.resp2 == 1f) "sim" else "não"}"
        holder.perg3.text = "Resp 3: ${if(dado.resp3 == 1f) "sim" else "não"}"
        holder.perg4.text = "Resp 4: ${if(dado.resp4 == 1f) "sim" else "não"}"
        holder.perg5.text = "Resp 5: ${if(dado.resp5 == 1f) "sim" else "não"}"
        holder.perg6.text = "Resp 6: ${if(dado.resp6 == 1f) "sim" else "não"}"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.rcy_layout,
                parent, false
            )

        return DadosViewHolder(v)
    }
}