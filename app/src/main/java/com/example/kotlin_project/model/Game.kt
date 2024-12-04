package com.example.kotlin_project.model

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int,
    val completed: Boolean = false // Si le jeu est terminé
)
