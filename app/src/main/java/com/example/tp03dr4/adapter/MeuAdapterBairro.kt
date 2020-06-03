package com.example.tp03dr4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp03dr4.R
import com.example.tp03dr4.classes.Criptografador
import com.example.tp03dr4.entidades.BairroTabela
import kotlinx.android.synthetic.main.rcy_vw_layout_bairro.view.*

class MeuAdapterBairro(val lista: Array<BairroTabela>):RecyclerView.Adapter<MeuAdapterBairro.DadosViewHolder>() {
    class DadosViewHolder(v: View):RecyclerView.ViewHolder(v){
        val campoBairro = v.txtVwBairroNome
        val campoPct = v.txtVwPorcentagem
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: DadosViewHolder, position: Int) {
        val dado = lista[position]
        holder.campoBairro.text = Criptografador().decipher(dado.bairro)
        holder.campoPct.text = "Porcentagem de cada pergunta, em ordem, é 1:${dado.avg1}" +
                " 2:${dado.avg2} 3:${dado.avg3} 4:${dado.avg4} 5:${dado.avg5} 6:${dado.avg6}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.rcy_vw_layout_bairro,
                parent, false)

        return DadosViewHolder(v)
    }
}