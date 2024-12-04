package com.example.kotlin_project.ui
import com.example.kotlin_project.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlin_project.viewmodel.MathOpViewModel
import com.example.kotlin_project.viewmodel.Operation

@Composable
fun MathOpPage(navController: NavController, viewModel: MathOpViewModel = viewModel()) {
    val operations = listOf(
        Operation("Addition", R.drawable.add),
        Operation("Soustraction", R.drawable.substruct),
        Operation("Multiplication", R.drawable.multiply),
        Operation("Division", R.drawable.divide)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Ajouter l'image de fond 'back7' qui couvre toute la taille de la page
        Image(
            painter = painterResource(id = R.drawable.back7),  // Utiliser votre image 'back7' ici
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,  // Cela permet à l'image de couvrir toute la zone
            modifier = Modifier.fillMaxSize()  // Cela rend l'image de fond aussi grande que la page
        )

        // Image de l'icône mathématique centrée en haut
        Image(
            painter = painterResource(id = R.drawable.mathop),
            contentDescription = "Math Operations",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        // LazyGrid pour afficher les cartes des opérations
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
                .padding(top = 150.dp) // Assurez-vous que les éléments ne se superposent pas
        ) {
            items(operations) { operation ->
                OperationCard(operation, navController)
            }
        }

        // Bouton pour revenir à la page des jeux
        Button(
            onClick = { navController.navigate("games_list") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .background(Color.Gray, shape = RoundedCornerShape(15.dp))
        ) {
            Text("Back to Game List", color = Color.White)
        }
    }
}

@Composable
fun OperationCard(operation: Operation, navController: NavController) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                shape = RoundedCornerShape(15.dp)
                clip = true
            }
            .clickable {
                when (operation.name) {
                    "Addition" -> navController.navigate("addition")
                    "Soustraction" -> navController.navigate("soustraction")
                    "Multiplication" -> navController.navigate("multiplication")
                    "Division" -> navController.navigate("division")
                }
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = operation.imageResId),
                contentDescription = operation.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(4.dp, Color.Gray, RoundedCornerShape(15.dp))
            )
        }
    }
}
