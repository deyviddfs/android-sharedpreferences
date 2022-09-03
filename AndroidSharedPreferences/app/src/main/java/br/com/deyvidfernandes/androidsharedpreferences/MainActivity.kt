package br.com.deyvidfernandes.androidsharedpreferences

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.deyvidfernandes.androidsharedpreferences.database.AppDatabase
import br.com.deyvidfernandes.androidsharedpreferences.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.alunosFragment, R.id.formAlunoFragment, R.id.perfilFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Perfil
        loadHeaderMenu()
    }

    fun loadHeaderMenu() {
        val appDb = AppDatabase.getDatabase(this)
        val perfil = appDb.perfilDao().get()
        if (perfil != null) {
            val nv = binding.navView
            val headerView = nv.getHeaderView(0)
            val textViewNome = headerView.findViewById<TextView>(R.id.textViewNome)
            textViewNome.setText(perfil.nome)
            val textViewEmail = headerView.findViewById<TextView>(R.id.textViewEmail)
            textViewEmail.setText(perfil.email)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}