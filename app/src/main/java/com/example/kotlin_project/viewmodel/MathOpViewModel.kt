package com.example.kotlin_project.viewmodel

import com.example.kotlin_project.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ViewModel pour gérer les données des opérations mathématiques
class MathOpViewModel : ViewModel() {

    // Liste des opérations avec leurs images
    private val _operations = listOf(
        Operation("Addition", R.drawable.add),
        Operation("Soustraction", R.drawable.substruct),
        Operation("Multiplication", R.drawable.multiply),
        Operation("Division", R.drawable.divide)
    )

    // Etat mutable pour stocker les opérations à afficher
    var operations by mutableStateOf(_operations)
        private set

    // Fonction pour récupérer les détails d'une opération (facultatif, si nécessaire)
    fun getOperationDetails(operationName: String): Operation? {
        return _operations.find { it.name == operationName }
    }
}

// Classe représentant une opération mathématique
data class Operation(val name: String, val imageResId: Int)
