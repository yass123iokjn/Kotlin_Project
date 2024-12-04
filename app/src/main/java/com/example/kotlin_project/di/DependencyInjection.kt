// Di/DependencyInjection.kt
package com.example.kotlin_project.di

import CalculationRepositoryImpl

import com.example.kotlin_project.domain.usecase.GetCalculationsUseCase


object AppModule {
    val repository = CalculationRepositoryImpl()
    val getCalculationsUseCase = GetCalculationsUseCase(repository)
}
