package com.example.kotlin_project.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions") // Le nom de la table dans la base de données
data class Question(
    @PrimaryKey val id: Int,
    val text: String,
    val options: List<String>,  // Room utilisera le TypeConverter pour cette liste
    val correctIndex: Int
)
