package com.example.kotlin_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_project.domain.models.Calculation
import com.example.kotlin_project.domain.usecase.GetCalculationsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculationViewModel(private val getCalculationsUseCase: GetCalculationsUseCase) : ViewModel() {
    private val _calculations = MutableStateFlow<List<Calculation>>(emptyList())
    val calculations: StateFlow<List<Calculation>> = _calculations

    fun loadCalculations(type: String) {
        viewModelScope.launch {
            _calculations.value = getCalculationsUseCase.execute(type)
        }
    }
}
