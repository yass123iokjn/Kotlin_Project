package com.example.kotlin_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_project.R

@Composable
fun AlphabetDetailPage(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Image de fond
        Image(
            painter = painterResource(id = R.drawable.back8),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Conteneur pour les cartes de jeux
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp) // Espacement entre les cartes
        ) {

            // Jeu Snake
            GameCard(
                imageResId = R.drawable.snake,
                title = "Snake Game",
                navController = navController, // Passer le navController à GameCard
                onClick = {
                    navController.navigate("snake_game")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Jeu Alphabet Quiz
            GameCard(
                imageResId = R.drawable.alphabet_quiz,
                title = "Alphabet Quiz",
                navController = navController, // Passer le navController à GameCard
                onClick = {
                    navController.navigate("alphabet_quiz")
                }
            )

            // Bouton pour revenir à la liste des jeux
            Button(
                onClick = { navController.navigate("games_list") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    text = "Back to Game List",
                    color = Color.White,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun GameCard(imageResId: Int, title: String, navController: NavController, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Image de l'icône de jeu (petite et arrondie)
            Image(
                painter = painterResource(imageResId),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp) // Taille réduite de l'icône
                    .clip(RoundedCornerShape(50)) // Image arrondie
                    .align(Alignment.Center)
            )

            // Titre du jeu
            Text(
                text = title,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
            )
        }
    }
}
