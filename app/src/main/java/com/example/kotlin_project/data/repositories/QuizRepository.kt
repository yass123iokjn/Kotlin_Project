package com.example.kotlin_project.data.repositories

import com.example.kotlin_project.domain.models.Question
import com.example.kotlin_project.domain.models.Answer

interface QuizRepository {
    suspend fun getQuestions(): List<Question>
    suspend fun saveQuestion(question: Question)  // Nouvelle méthode pour sauvegarder des questions
    suspend fun saveAnswer(answer: Answer)
}
