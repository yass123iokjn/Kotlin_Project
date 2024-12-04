package com.example.kotlin_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_project.R
import com.example.kotlin_project.model.Game

class GamesViewModel : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> get() = _games

    init {
        loadGames() // Charger les jeux au démarrage
    }

    private fun loadGames() {
        // Exemple de données. Remplacez-les par des données de la base de données.
        _games.value = listOf(
            Game(1, "Formes Géométriques", "Apprends les formes", R.drawable.shapes_game),
            Game(2, "Opérations Mathématiques", "Résous des opérations", R.drawable.math_operations),
            Game(3, "Alphabet", "Découvre les lettres", R.drawable.alphabet_game),
            Game(4, "Jeu de Quiz", "Connaissance generale", R.drawable.quiz_game)
        )
    }
}
