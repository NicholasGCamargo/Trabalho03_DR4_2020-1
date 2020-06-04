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
import com.example.tp03dr4.classes.ConfereBairroTratamento
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

    inner class PegarBDBairro():AsyncTask<Unit, Unit, MutableList<BairroTabela>?>() {
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
                    Log.d("PEGANDO DB", passar[passar.lastIndex].bairro.getClearText() ?: "Null")
                }
                return passar
            } catch (e: Exception) {
                Log.e("ERRO DE DATABASE", e.message!!)
                return null
            }
        }

        override fun onPostExecute(result: MutableList<BairroTabela>?) {
            super.onPostExecute(result)
            if (result == null) {
                Toast.makeText(
                    activity!!.applicationContext,
                    "Ocorreu algum erro",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                //tratamento de dados
                val classTratamento = ConfereBairroTratamento()
                var index = 0

                while (index < result.size) {
                    if (result[index].bairro.getClearText() in classTratamento.bairros) {
                        //bairro já existe dentro
                        var i = 0
                        while (i < classTratamento.resultado.size) {
                            if (classTratamento.resultado[i].bairro.getClearText() == result[index].bairro.getClearText()) {
                                classTratamento.qtd[i] += 1
                                classTratamento.resultado[i].avg1 =
                                    (classTratamento.resultado[i].avg1 + result[index].avg1) / classTratamento.qtd[i]
                                classTratamento.resultado[i].avg2 =
                                    (classTratamento.resultado[i].avg2 + result[index].avg2) / classTratamento.qtd[i]
                                classTratamento.resultado[i].avg3 =
                                    (classTratamento.resultado[i].avg3 + result[index].avg3) / classTratamento.qtd[i]
                                classTratamento.resultado[i].avg4 =
                                    (classTratamento.resultado[i].avg4 + result[index].avg4) / classTratamento.qtd[i]
                                classTratamento.resultado[i].avg5 =
                                    (classTratamento.resultado[i].avg5 + result[index].avg5) / classTratamento.qtd[i]
                                classTratamento.resultado[i].avg6 =
                                    (classTratamento.resultado[i].avg6 + result[index].avg6) / classTratamento.qtd[i]
                            }
                            i++
                        }
                    } else {
                        //bairro não existe dentro
                        classTratamento.bairros.add(result[index].bairro.getClearText()!!)
                        classTratamento.qtd.add(1)
                        classTratamento.resultado.add(result[index])
                    }

                    index++
                }

                rcyVwBairros.adapter = MeuAdapterBairro(classTratamento.resultado)
                rcyVwBairros.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}
