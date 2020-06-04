package com.example.tp03dr4.ui.SuasListagens

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
import com.example.tp03dr4.BD.MyDatabaseService
import com.example.tp03dr4.R
import com.example.tp03dr4.ViewModel.MeuViewModel
import com.example.tp03dr4.adapter.AdapterListagemUsuario
import com.example.tp03dr4.entidades.TabelaPrincipal
import kotlinx.android.synthetic.main.fragment_dashboard.*

class ListagensFragment : Fragment() {

    private lateinit var meuViewModel: MeuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        activity?.let {
            meuViewModel = ViewModelProviders.of(it)[MeuViewModel::class.java]
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PegarBD().execute(meuViewModel.idAtual)

    }

    inner class PegarBD(): AsyncTask<String, Unit, Array<TabelaPrincipal>?>(){
        override fun doInBackground(vararg params: String?): Array<TabelaPrincipal>? {
            return try {
                val db =  MyDatabaseService.getInstance(context!!)

                return db.tabelaDAO().all(params[0]!!)
            }catch (e: Exception){
                Log.e("ERRO DE DATABASE", e.cause?.message ?: e.message!!)
                null
            }
        }

        override fun onPostExecute(result: Array<TabelaPrincipal>?) {
            super.onPostExecute(result)
            if(result == null){
                Toast.makeText(
                    activity!!.applicationContext,
                    "Ocorreu algum erro",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                rcyVwDashboard.adapter = AdapterListagemUsuario(result)
                rcyVwDashboard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }
        }
    }
}
