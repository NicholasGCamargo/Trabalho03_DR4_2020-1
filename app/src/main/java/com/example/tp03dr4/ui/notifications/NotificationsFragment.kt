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

    inner class PegarBDBairro():AsyncTask<Unit, Unit, Array<BairroTabela>?>(){
        override fun doInBackground(vararg params: Unit?): Array<BairroTabela>? {
            return try {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    database::class.java,
                    "appdatabase.db"
                ).build()

                db.tabelaDAO().bairro()
            }catch (e: Exception){
                Log.e("ERRO DE DATABASE", e.message!!)
                null
            }
        }

        override fun onPostExecute(result: Array<BairroTabela>?) {
            super.onPostExecute(result)
            if(result == null){
                Toast.makeText(
                    activity!!.applicationContext,
                    "Ocorreu algum erro",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                rcyVwBairros.adapter = MeuAdapterBairro(result)
                rcyVwBairros.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

}
