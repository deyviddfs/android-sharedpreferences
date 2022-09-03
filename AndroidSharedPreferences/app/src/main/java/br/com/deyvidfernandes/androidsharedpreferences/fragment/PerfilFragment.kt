package br.com.deyvidfernandes.androidsharedpreferences.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.deyvidfernandes.androidsharedpreferences.MainActivity
import br.com.deyvidfernandes.androidsharedpreferences.R
import br.com.deyvidfernandes.androidsharedpreferences.database.AppDatabase
import br.com.deyvidfernandes.androidsharedpreferences.database.Perfil
import br.com.deyvidfernandes.androidsharedpreferences.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root
        val appDb = AppDatabase.getDatabase(view.context)

        val perfil = appDb.perfilDao().get()
        if(perfil!=null){
            binding.editTextNome.setText(perfil.nome)
            binding.editTextEmail.setText(perfil.email)
        }
        else{
            val novoPerfil = Perfil(1, getString(R.string.perfil_nome), getString(R.string.perfil_email))
            appDb.perfilDao().insert(novoPerfil)
            binding.editTextNome.setText(novoPerfil.nome)
            binding.editTextEmail.setText(novoPerfil.email)
        }

        binding.buttonSalvar.setOnClickListener {
            Toast.makeText(view.context, R.string.registro_atualizado_com_sucesso, Toast.LENGTH_LONG).show()
            val nome = binding.editTextNome.text.toString()
            val rm = binding.editTextEmail.text.toString()
            val atualizaPerfil = Perfil(1, nome, rm)
            appDb.perfilDao().update(atualizaPerfil)
            //Atualiza Header
            (requireActivity() as MainActivity).loadHeaderMenu()
        }

        return view
    }
}