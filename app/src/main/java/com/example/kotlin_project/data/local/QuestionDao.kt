package com.example.kotlin_project.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_project.domain.models.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")  // Assurez-vous que votre table s'appelle 'questions'
    suspend fun getAll(): List<Question>

    @Insert
    suspend fun insert(question: Question)  // Méthode d'insertion
}
