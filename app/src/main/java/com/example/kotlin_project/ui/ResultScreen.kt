package com.example.kotlin_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_project.R

@Composable
fun ResultScreen(navController: NavController, score: Float, status: String) {
    // Déterminer si le score est supérieur ou inférieur à 50% pour définir le statut
    val finalStatus = if (score > 50) "Réussi" else "Échoué"

    // Définir la ressource d'image en fonction du statut
    val imageResource = if (finalStatus == "Réussi") R.drawable.happy else R.drawable.sad
    val imageContentDescription = if (finalStatus == "Réussi") "Success Icon" else "Failure Icon"
    val imageColor = if (finalStatus == "Réussi") Color.Green else Color.Red

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFffebcd)) // Définir la couleur de fond de la page
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Votre score: $score %",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Utilisation de Image pour afficher des images personnalisées selon le statut
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = imageContentDescription,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Statut: $finalStatus",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = imageColor
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Boutons pour naviguer vers la page des jeux ou refaire le quiz
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        // Naviguer vers la page des jeux
                        navController.navigate("games_list")
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Back to games")
                }

                Button(
                    onClick = {
                        navController.navigate("quiz_screen")
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Requiz")
                }
            }
        }
    }
}
