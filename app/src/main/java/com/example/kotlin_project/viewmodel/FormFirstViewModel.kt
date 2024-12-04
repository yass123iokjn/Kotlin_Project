package com.example.kotlin_project.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kotlin_project.R

class FormFirstViewModel : ViewModel() {

    // Liste des formes et des images correspondantes
    val shapeImages = mapOf(
        "circle" to R.drawable.cercle,
        "square" to R.drawable.square,
        "triangle" to R.drawable.triangle,
        "trapeze" to R.drawable.trapeze,
        "rectangle" to R.drawable.rectangle,
        "pentagon" to R.drawable.pentagone
    )

    // Liste des réponses de l'utilisateur
    var answers = mutableStateOf(mapOf<String, String>())

    // État de validation des réponses
    var validationState = mutableStateOf(mapOf<String, Boolean>())

    // Gérer l'état du formulaire après la validation
    var isValidated = mutableStateOf(false)

    // Méthode pour valider les réponses
    fun validateAnswers(shapes: List<String>) {
        isValidated.value = true
        validationState.value = shapes.associateWith { shape ->
            answers.value[shape]?.trim()?.lowercase() == shape
        }
    }
}
