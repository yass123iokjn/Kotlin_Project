package com.example.kotlin_project.data.repositories

import com.example.kotlin_project.domain.models.Question
import com.example.kotlin_project.domain.models.Answer
import com.example.kotlin_project.data.local.QuestionDao
import com.example.kotlin_project.data.local.AnswerDao
import android.util.Log
class QuizRepositoryImpl(
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao
) : QuizRepository {

    override suspend fun getQuestions(): List<Question> {
        return questionDao.getAll()
    }

    override suspend fun saveQuestion(question: Question) {
        questionDao.insert(question)  // Insérer la question dans la base de données
    }

    override suspend fun saveAnswer(answer: Answer) {
        Log.d("QuizRepositoryImpl", "Inserting answer: ${answer.questionId}, ${answer.option}")

        answerDao.insert(answer)
    }
}
