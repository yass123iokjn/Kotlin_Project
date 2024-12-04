package com.example.kotlin_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController


@Composable
fun HomePage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Dégradé ou couleur de fond
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFf5e63d), Color(0xFF3d5afe))
                )
            )
    ) {
        // Fond d'écran (image en arrière-plan)
        Image(
            painter = painterResource(id = R.drawable.backgroundimg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize()
        )

        // Avatar centré
        Image(
            painter = painterResource(id = R.drawable.avatar_boy2),
            contentDescription = "Avatar Image",
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center)
        )

        // Bouton de navigation en bas de l'écran (rectangulaire avec bordure en gras)
        Button(
            onClick = { navController.navigate("games_list") },
            shape = RoundedCornerShape(16.dp),  // Coins arrondis
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
                .fillMaxWidth(0.8f)  // Largeur ajustée à 80% de la largeur de l'écran
                .height(60.dp),  // Hauteur du bouton
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF03A9F4) // Couleur de texte et de la bordure bleue
            ),
            border = BorderStroke(3.dp, Color(0xFF03A9F4))  // Bordure plus épaisse
        ) {
            Text("Let's Start", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
