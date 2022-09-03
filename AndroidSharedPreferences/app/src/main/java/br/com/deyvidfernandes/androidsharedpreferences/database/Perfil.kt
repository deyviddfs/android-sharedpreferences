package br.com.deyvidfernandes.androidsharedpreferences.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfil")
data class Perfil(@PrimaryKey var id: Int = 1,
                  @NonNull @ColumnInfo(name = "name") val nome: String,
                  @NonNull @ColumnInfo(name = "email")val email: String){}