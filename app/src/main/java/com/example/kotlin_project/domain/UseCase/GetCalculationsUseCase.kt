// Domain/UseCase/GetCalculationsUseCase.kt
package com.example.kotlin_project.domain.usecase

import com.example.kotlin_project.domain.Repository.CalculationRepository
import com.example.kotlin_project.domain.models.Calculation

class GetCalculationsUseCase(private val repository: CalculationRepository) {
    fun execute(type: String): List<Calculation> {
        return when (type) {
            "Addition" -> repository.getAdditionCalculations()
            "Soustraction" -> repository.getSubtractionCalculations()
            "Multiplication" -> repository.getMultiplicationCalculations()
            "Division" -> repository.getDivisionCalculations()
            else -> emptyList()
        }
    }
}
