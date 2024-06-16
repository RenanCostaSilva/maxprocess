package br.com.renancsdev.maxprocessagenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.renancsdev.maxprocessagenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        carregarLayout()
        carregarNavcontroller()


    }

    private fun carregarLayout(){
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun carregarNavcontroller(){
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cadastro, R.id.navigation_listar, R.id.navigation_alterar , R.id.navigation_excluir
            )
        )
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment_activity_main), appBarConfiguration)
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))
    }
}