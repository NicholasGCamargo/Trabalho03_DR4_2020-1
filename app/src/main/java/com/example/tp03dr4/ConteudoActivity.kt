package com.example.tp03dr4

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tp03dr4.ViewModel.MeuViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class ConteudoActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var meuViewModel: MeuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conteudo)

        mAuth = FirebaseAuth.getInstance()

        meuViewModel = ViewModelProviders.of(this)[MeuViewModel::class.java]
        meuViewModel.idAtual = mAuth.currentUser!!.uid

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.findViewById<View>(R.id.logout_menu).setOnClickListener {
            mAuth.signOut()
            finish()
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}
