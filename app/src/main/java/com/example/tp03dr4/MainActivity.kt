package com.example.tp03dr4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        currentUser = mAuth.currentUser
        if(currentUser == null){
            //usuario nao esta conectado
            btnCriarHome.setOnClickListener {
                //cria a conta do usuario
                val email = inserirEmailHome.text.toString()
                val senha = inserirSenhaHome.text.toString()

                inserirSenhaHome.setText("")
                mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
                    if(it.isSuccessful) {
                        //funcionou
                        currentUser = mAuth.currentUser
                        Toast.makeText(
                            this,
                            "Conta criada com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else {
                        //deu algum erro
                        Log.w("create user fail", it.exception)
                        Toast.makeText(
                            this,
                            "Ocorreu algum erro com a criação de conta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            btnHomeLogar.setOnClickListener {
                //loga o usuario

                val email = inserirEmailHome.text.toString()
                val senha = inserirSenhaHome.text.toString()
                inserirEmailHome.setText("")
                inserirSenhaHome.setText("")
                mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //conseguiu conectar
                        val intent = Intent(this, ConteudoActivity::class.java)
                        startActivity(intent)
                    }else {
                        //não conseguiu conectar
                        Log.w("falha ao conectar", it.exception)
                        Toast.makeText(
                            this,
                            "Seu email ou senha estão erradas, ou nossos servidores estão fora do ar.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }else{
            //usuario ja esta conectado
            val intent = Intent(this, ConteudoActivity::class.java)
            startActivity(intent)
        }
    }
}
