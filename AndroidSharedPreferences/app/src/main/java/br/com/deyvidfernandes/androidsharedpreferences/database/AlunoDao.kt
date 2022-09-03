package br.com.deyvidfernandes.androidsharedpreferences.database

import androidx.room.*

@Dao
interface AlunoDao {
    @Query("SELECT * FROM aluno ORDER BY nome ASC")
    fun getAll(): List<Aluno>

    @Insert
    fun insert(vararg aluno: Aluno)

    @Query("SELECT * FROM aluno WHERE id = :id")
    fun getById(id:Int): Aluno

    @Delete
    fun delete(aluno: Aluno)

    @Update
    fun update (aluno: Aluno)
}