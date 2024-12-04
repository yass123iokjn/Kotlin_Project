package com.example.kotlin_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_project.domain.models.Question
import com.example.kotlin_project.domain.models.Answer
import com.example.kotlin_project.data.repositories.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    var questions: List<Question> = emptyList()
    var selectedOptionIndex: Int? = null
    var isCorrectAnswer: Boolean? = null

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            questions = quizRepository.getQuestions()
        }
    }

    fun checkAnswer(questionIndex: Int, selectedIndex: Int): Boolean {
        val question = questions[questionIndex]
        val correct = selectedIndex == question.correctIndex
        viewModelScope.launch {
            saveAnswer(question.id, selectedIndex, correct)
        }
        return correct
    }

    private suspend fun saveAnswer(questionId: Int, selectedIndex: Int, isCorrect: Boolean) {
        val answer = Answer(questionId = questionId, option = selectedIndex.toString())
        quizRepository.saveAnswer(answer)
    }

    fun resetAnswer() {
        selectedOptionIndex = null
        isCorrectAnswer = null
    }
}
