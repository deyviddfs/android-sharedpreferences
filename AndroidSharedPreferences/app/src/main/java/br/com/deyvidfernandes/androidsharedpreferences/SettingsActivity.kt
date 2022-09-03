package br.com.deyvidfernandes.androidsharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.deyvidfernandes.androidsharedpreferences.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var shared : SharedPreferences
    private lateinit var binding: ActivitySettingsBinding
    val SHARED_PREFERENCE_NAME = "ALUNO"
    val SHARED_PREFERENCE_APELIDO = "apelido"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shared = getSharedPreferences(SHARED_PREFERENCE_NAME , Context.MODE_PRIVATE)

        var apelido = shared.getString(SHARED_PREFERENCE_APELIDO , "")
        binding.editTextApelido.setText(apelido)

        binding.buttonSalvar.setOnClickListener {
            val edit = shared.edit()
            edit.putString(SHARED_PREFERENCE_APELIDO , binding.editTextApelido.text.toString())
            edit.apply()
            Toast.makeText(this, R.string.configuracoes_atualizadas_com_sucesso, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}