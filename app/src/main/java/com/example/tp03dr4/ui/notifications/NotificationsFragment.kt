package com.example.tp03dr4.ui.notifications

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tp03dr4.BD.database
import com.example.tp03dr4.R
import com.example.tp03dr4.ViewModel.MeuViewModel
import com.example.tp03dr4.adapter.MeuAdapterBairro
import com.example.tp03dr4.classes.TratamentoBairros
import com.example.tp03dr4.entidades.BairroTabela
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var meuViewModel: MeuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        activity?.let {
            meuViewModel = ViewModelProviders.of(it)[MeuViewModel::class.java]
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PegarBDBairro().execute()

    }

    inner class PegarBDBairro():AsyncTask<Unit, Unit, MutableList<BairroTabela>?>(){
        override fun doInBackground(vararg params: Unit?): MutableList<BairroTabela>? {
            try {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    database::class.java,
                    "appdatabase.db"
                ).build()

                val bairros = TratamentoBairros(db.tabelaDAO().allBairro()).tratarDados()

                val passar = mutableListOf<BairroTabela>()
                bairros.forEach {
                    passar.add(db.tabelaDAO().bairro(it)[0])
                    Log.d("PEGANDO DB", passar[passar.lastIndex].bairro.getClearText() ?:"Null")
                }
                return passar
            }catch (e: Exception){
                Log.e("ERRO DE DATABASE", e.message!!)
                return null
            }
        }

        override fun onPostExecute(result: MutableList<BairroTabela>?) {
            super.onPostExecute(result)
            if(result == null){
                Toast.makeText(
                    activity!!.applicationContext,
                    "Ocorreu algum erro",
                    Toast.LENGTH_LONG
                ).show()
            }else{

                //tratamento de dados
                val bairros: MutableList<String> = mutableListOf()
                val qtd: MutableList<Int> = mutableListOf()
                val resultado: MutableList<BairroTabela> = mutableListOf()

                var index = 0

                while (index < result.size){
                    if(result[index].bairro.getClearText() in bairros){
                        //bairro já existe dentro
                        var i = 0
                        while(i < resultado.size){
                            if(resultado[i].bairro.getClearText() == result[index].bairro.getClearText()){
                                qtd[i] += 1
                                resultado[i].avg1 = (resultado[i].avg1 + result[index].avg1) / qtd[i]
                                resultado[i].avg2 = (resultado[i].avg2 + result[index].avg2) / qtd[i]
                                resultado[i].avg3 = (resultado[i].avg3 + result[index].avg3) / qtd[i]
                                resultado[i].avg4 = (resultado[i].avg4 + result[index].avg4) / qtd[i]
                                resultado[i].avg5 = (resultado[i].avg5 + result[index].avg5) / qtd[i]
                                resultado[i].avg6 = (resultado[i].avg6 + result[index].avg6) / qtd[i]
                            }
                            i++
                        }
                    }else{
                        //bairro não existe dentro
                        bairros.add(result[index].bairro.getClearText()!!)
                        qtd.add(1)
                        resultado.add(result[index])
                    }

                    index++
                }

                rcyVwBairros.adapter = MeuAdapterBairro(resultado)
                rcyVwBairros.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}
