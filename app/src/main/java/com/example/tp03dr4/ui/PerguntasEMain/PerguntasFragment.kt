package com.example.tp03dr4.ui.PerguntasEMain

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tp03dr4.BD.MyDatabaseService
import com.example.tp03dr4.R
import com.example.tp03dr4.ViewModel.MeuViewModel
import com.example.tp03dr4.classes.CriptoString
import com.example.tp03dr4.entidades.TabelaPrincipal
import kotlinx.android.synthetic.main.fragment_home.*

class PerguntasFragment : Fragment() {

    lateinit var meuViewModel: MeuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        activity?.let {
            meuViewModel = ViewModelProviders.of(it)[MeuViewModel::class.java]
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner1 = spinnerHome
        val spinner2 = spinnerBairro
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_escolhas,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner1.adapter = it
            }

            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_bairro,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner2.adapter = it
            }
        }


        btnEnviarAval.setOnClickListener {
            val perg1 = if(pergunta.checkedRadioButtonId == R.id.respostaPergunta) 1f else 0f
            val perg2 = if(pergunta2.checkedRadioButtonId == R.id.respostaPergunta21) 1f else 0f
            val perg3 = if(pergunta3.checkedRadioButtonId == R.id.respostaPergunta31) 1f else 0f
            val perg4 = if(pergunta4.checkedRadioButtonId == R.id.respostaPergunta41) 1f else 0f
            val perg5 = if(pergunta5.checkedRadioButtonId == R.id.respostaPergunta51) 1f else 0f
            val perg6 = if(pergunta6.checkedRadioButtonId == R.id.respostaPergunta61) 1f else 0f
            val restaurant = CriptoString()
            val bairro = CriptoString()

            restaurant.setClearText(spinner1.selectedItem.toString())
            bairro.setClearText(spinner2.selectedItem.toString())

            timeoutBtn()
            InserirBD().execute(TabelaPrincipal(meuViewModel.idAtual, restaurant, bairro, perg1, perg2, perg3, perg4, perg5, perg6))

        }
    }

    private fun timeoutBtn(){
        btnEnviarAval.isClickable = !btnEnviarAval.isClickable

    }

    inner class InserirBD(): AsyncTask<TabelaPrincipal, Unit, Boolean>(){
        override fun doInBackground(vararg params: TabelaPrincipal?):Boolean {
            return try {
                val db = MyDatabaseService.getInstance(context!!)

                db.tabelaDAO().insert(params[0]!!)

                true
            }catch (e: Exception){
                Log.e("ERRO DE DATABASE", e.message!!)
                false
            }
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            val toastmsg = if (result != null) "Dado inserido" else "Ocorreu algum erro"
            Toast.makeText(
                activity!!.applicationContext,
                toastmsg,
                Toast.LENGTH_SHORT
            ).show()
            timeoutBtn()
        }
    }

}
