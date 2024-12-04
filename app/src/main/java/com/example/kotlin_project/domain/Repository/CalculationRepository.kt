package com.example.kotlin_project.domain.Repository

import com.example.kotlin_project.domain.models.Calculation

interface CalculationRepository {
    fun getAdditionCalculations(): List<Calculation>
    fun getSubtractionCalculations(): List<Calculation>
    fun getMultiplicationCalculations(): List<Calculation>
    fun getDivisionCalculations(): List<Calculation>
}