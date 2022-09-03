package br.com.deyvidfernandes.androidsharedpreferences.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aluno")
data class Aluno(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                 @NonNull @ColumnInfo val nome: String,
                 @NonNull @ColumnInfo(name = "rm")val rm: String,
                 @NonNull @ColumnInfo(name = "curso")val curso: String,
                 @NonNull @ColumnInfo(name = "ano")val ano: String,
                 @NonNull @ColumnInfo(name = "turma")val turma: String){}