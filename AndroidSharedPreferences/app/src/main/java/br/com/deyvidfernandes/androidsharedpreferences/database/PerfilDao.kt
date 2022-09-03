package br.com.deyvidfernandes.androidsharedpreferences.database

import androidx.room.*

@Dao
interface PerfilDao {
    @Query("SELECT * FROM perfil WHERE id = 1")
    fun get(): Perfil;

    @Insert
    fun insert(vararg perfil: Perfil)

    @Update
    fun update(perfil: Perfil)
}