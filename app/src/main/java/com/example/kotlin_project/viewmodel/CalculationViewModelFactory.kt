package com.example.kotlin_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_project.domain.usecase.GetCalculationsUseCase

class CalculationViewModelFactory(
    private val getCalculationsUseCase: GetCalculationsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculationViewModel::class.java)) {
            return CalculationViewModel(getCalculationsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
