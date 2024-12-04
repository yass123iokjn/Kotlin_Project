package com.example.kotlin_project.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_project.domain.models.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)  // Ignore si l'id existe déjà
    suspend fun insert(answer: Answer)

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    suspend fun getAnswerByQuestionId(questionId: Int): Answer?

    @Query("DELETE FROM answers")
    suspend fun clearAllAnswers()  // Méthode pour nettoyer la table
}
