package com.example.kotlin_project.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_project.domain.Repository.CalculationRepository
import com.example.kotlin_project.ui.Operations.MathOperationPage
import com.example.kotlin_project.viewmodel.CalculationViewModel
import com.example.kotlin_project.viewmodel.CalculationViewModelFactory
import com.example.kotlin_project.domain.usecase.GetCalculationsUseCase

fun NavGraphBuilder.mathOperationsNav(navController: NavHostController, calculationRepository: CalculationRepository) {
    composable("Addition") {
        val calculationViewModel: CalculationViewModel = viewModel(factory = CalculationViewModelFactory(GetCalculationsUseCase(calculationRepository)))
        MathOperationPage(navController, "Addition", calculationViewModel)
    }
    composable("Soustraction") {
        val calculationViewModel: CalculationViewModel = viewModel(factory = CalculationViewModelFactory(GetCalculationsUseCase(calculationRepository)))
        MathOperationPage(navController, "Soustraction", calculationViewModel)
    }
    composable("Multiplication") {
        val calculationViewModel: CalculationViewModel = viewModel(factory = CalculationViewModelFactory(GetCalculationsUseCase(calculationRepository)))
        MathOperationPage(navController, "Multiplication", calculationViewModel)
    }
    composable("Division") {
        val calculationViewModel: CalculationViewModel = viewModel(factory = CalculationViewModelFactory(GetCalculationsUseCase(calculationRepository)))
        MathOperationPage(navController, "Division", calculationViewModel)
    }
}
