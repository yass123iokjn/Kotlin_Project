package com.example.kotlin_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_project.data.repositories.QuizRepository

class QuizViewModelFactory(private val quizRepository: QuizRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            QuizViewModel(quizRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
