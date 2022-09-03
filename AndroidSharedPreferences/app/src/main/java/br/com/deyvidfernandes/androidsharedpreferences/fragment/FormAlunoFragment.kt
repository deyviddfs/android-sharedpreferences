package br.com.deyvidfernandes.androidsharedpreferences.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
        var alunoId = 0

        val bundle = this.arguments
        if (bundle != null) {
            alunoId = bundle.getInt("aluno_id", 0)
        }

        if (alunoId > 0){
            binding.buttonExcluir.visibility = View.VISIBLE
            binding.buttonAtualizar.visibility = View.VISIBLE
            binding.buttonSalvar.visibility = View.INVISIBLE
            val aluno = appDb.alunoDao().getById(alunoId)
            loadForm(aluno)
        }

        binding.buttonSalvar.setOnClickListener {
            if(validarCamposObrigatorios(view)){
                Toast.makeText(view.context, R.string.registro_cadastrado_com_sucesso, Toast.LENGTH_LONG).show()
                val novoAluno = getFormAluno(0)
                appDb.alunoDao().insert(novoAluno)
                clearForm()
            }
        }

        binding.buttonExcluir.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setMessage(R.string.tem_certeza_que_deseja_excluir)
                .setCancelable(false)
                .setPositiveButton(R.string.sim) { dialog, id ->
                    val aluno = Aluno(alunoId, "", "", "", "", "")
                    appDb.alunoDao().delete(aluno)
                    clearForm()
                    it.findNavController().navigate(R.id.alunosFragment)
                }
                .setNegativeButton(R.string.nao) { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        binding.buttonAtualizar.setOnClickListener {
            if(validarCamposObrigatorios(view)){
                Toast.makeText(view.context, R.string.registro_atualizado_com_sucesso, Toast.LENGTH_LONG).show()
                val aluno = getFormAluno(alunoId)
                appDb.alunoDao().update(aluno)
                clearForm()
                it.findNavController().navigate(R.id.action_formAlunoFragment_to_alunosFragment)
            }
        }

        return view
    }

    private fun getFormAluno(id: Int): Aluno {
        val nome = binding.editTextNome.text.toString()
        val rm = binding.editTextRM.text.toString()
        val curso = binding.editTextCurso.text.toString()
        val ano = binding.editTextAno.text.toString()
        val turma = binding.editTextTurma.text.toString()
        val novoAluno = Aluno(id, nome, rm, curso, ano, turma)
        return novoAluno
    }

    private fun clearForm() {
        //Limpa o formulário
        binding.editTextNome.setText(R.string.vazio)
        binding.editTextRM.setText(R.string.vazio)
        binding.editTextCurso.setText(R.string.vazio)
        binding.editTextAno.setText(R.string.vazio)
        binding.editTextTurma.setText(R.string.vazio)
    }

    private fun loadForm(aluno: Aluno){
        binding.editTextNome.setText(aluno.nome)
        binding.editTextRM.setText(aluno.rm)
        binding.editTextCurso.setText(aluno.curso)
        binding.editTextAno.setText(aluno.ano)
        binding.editTextTurma.setText(aluno.turma)
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