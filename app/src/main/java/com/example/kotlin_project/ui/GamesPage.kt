package com.example.kotlin_project.ui
import com.example.kotlin_project.R

import com.example.kotlin_project.model.Game
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.draw.clip
import com.example.kotlin_project.viewmodel.GamesViewModel
import androidx.navigation.compose.composable
import androidx.navigation.NavController

@Composable
fun GamesPage(navController: NavController, viewModel: GamesViewModel = viewModel()) {
    val games = viewModel.games.value ?: emptyList()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.back1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
                .padding(top = 250.dp)
        ) {
            items(games) { game ->
                GameCard(game, navController)  // Passe navController ici
            }
        }
    }
}

@Composable
fun GameCard(game: Game, navController: NavController) {
    Card(
        modifier = Modifier
            .size(120.dp)  // La carte aura la même taille que l'image
            .graphicsLayer {
                shadowElevation = 60.dp.toPx()  // Augmenter l'effet de l'ombre pour un effet sortant
                shape = RoundedCornerShape(15.dp)  // Coins arrondis
                clip = true
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)  // Carte sans fond
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .clickable {
                    if (game.title == "Formes Géométriques") {
                        navController.navigate("form_first")
                    }
                    if (game.title == "Jeu de Quiz") {
                        navController.navigate("quiz_screen")
                    }
                    if (game.title == "Alphabet") {
                        navController.navigate("alphabet_game")
                    }
                    if (game.title == "Opérations Mathématiques") {
                        navController.navigate("math_operations")
                    }
                }
        ) {
            Image(
                painter = painterResource(game.imageResId),
                contentDescription = game.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(4.dp, Color.Gray, RoundedCornerShape(15.dp))
            )
        }
    }
}