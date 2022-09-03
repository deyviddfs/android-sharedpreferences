package br.com.deyvidfernandes.androidsharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.deyvidfernandes.androidsharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var shared : SharedPreferences
    private lateinit var binding: ActivityMainBinding
    val SHARED_PREFERENCE_NAME = "ALUNO"
    val SHARED_PREFERENCE_APELIDO = "apelido"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shared = getSharedPreferences(SHARED_PREFERENCE_NAME , Context.MODE_PRIVATE)

        var apelido = shared.getString(SHARED_PREFERENCE_APELIDO , "")
        binding.textViewApelido.setText(apelido)
    }
}