package br.com.deyvidfernandes.androidsharedpreferences.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import br.com.deyvidfernandes.androidsharedpreferences.R
import br.com.deyvidfernandes.androidsharedpreferences.database.Aluno
import br.com.deyvidfernandes.androidsharedpreferences.database.AppDatabase
import br.com.deyvidfernandes.androidsharedpreferences.databinding.FragmentFormAlunoBinding


class FormAlunoFragment : Fragment() {

    lateinit var binding: FragmentFormAlunoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormAlunoBinding.inflate(inflater, container, false)
        val view = binding.root
        val appDb = AppDatabase.getDatabase(view.context)

        //TODO: Implementar excluir/atualizar dados


        binding.buttonSalvar.setOnClickListener {
            if(validarCamposObrigatorios(view)){
                Toast.makeText(view.context, R.string.registro_cadastrado_com_sucesso, Toast.LENGTH_LONG).show()
                val nome = binding.editTextNome.text.toString()
                val rm = binding.editTextRM.text.toString()
                val curso = binding.editTextCurso.text.toString()
                val ano = binding.editTextAno.text.toString()
                val turma = binding.editTextTurma.text.toString()
                val novoAluno = Aluno(0, nome, rm, curso, ano, turma)
                appDb.alunoDao().insert(novoAluno)

                //Limpa o formulário
                binding.editTextNome.setText(R.string.vazio)
                binding.editTextRM.setText(R.string.vazio)
                binding.editTextCurso.setText(R.string.vazio)
                binding.editTextAno.setText(R.string.vazio)
                binding.editTextTurma.setText(R.string.vazio)
            }
        }

        return view
    }

    fun validarCamposObrigatorios(view: ConstraintLayout): Boolean{
        if(binding.editTextNome.text.isNullOrEmpty()) {
            Toast.makeText(view.context, R.string.campo_nome_e_obrigatorio, Toast.LENGTH_LONG)
                .show()
            binding.editTextNome.requestFocus()
            return false
        }
        return true
    }
}