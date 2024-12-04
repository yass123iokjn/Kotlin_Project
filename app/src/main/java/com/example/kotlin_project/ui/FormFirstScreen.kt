package com.example.kotlin_project.ui

import com.example.kotlin_project.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_project.viewmodel.FormFirstViewModel

@Composable
fun FormFirstScreen(viewModel: FormFirstViewModel = viewModel(), onNavigateToGameList: () -> Unit) {
    val shapes = listOf("circle", "square", "triangle", "trapeze", "rectangle", "pentagon")
    val shapeImages = viewModel.shapeImages
    val answers by remember { viewModel.answers }
    val validationState by remember { viewModel.validationState }
    val isValidated by remember { viewModel.isValidated }

    // Calcul du score après validation
    val correctCount = validationState.count { it.value }

    var showDialog by remember { mutableStateOf(false) } // Déclaration de showDialog pour afficher la boîte de dialogue
    var firstValidationClicked by remember { mutableStateOf(false) } // Suivi du premier clic de validation

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Ajouter l'image de fond à l'arrière-plan
        Image(
            painter = painterResource(id = R.drawable.back11),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajuste l'image au fond
        )

        // Contenu de la colonne par-dessus l'image de fond
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Affichage des formes par lignes de deux
            for (row in shapes.chunked(2)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { shape ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.4f)
                        ) {
                            var textValue by remember { mutableStateOf(answers[shape] ?: "") }

                            // Champ de texte
                            BasicTextField(
                                value = textValue,
                                onValueChange = { newText ->
                                    textValue = newText
                                    viewModel.answers.value = answers + (shape to newText)
                                },
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.8f), shape = MaterialTheme.shapes.medium)
                                    .border(
                                        width = 2.dp,
                                        color = if (isValidated) {
                                            if (validationState[shape] == true) Color.Green else Color.Red
                                        } else Color.Gray,
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .height(40.dp),
                                textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Affichage de l'image
                            val imageId = shapeImages[shape] ?: R.drawable.placeholder
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = shape,
                                modifier = Modifier.size(100.dp)
                            )

                            // Affichage de la réponse correcte si validée
                            if (isValidated && validationState[shape] == true) {
                                Text(
                                    text = "Correct!",
                                    color = Color.Green,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            } else if (isValidated && validationState[shape] == false) {
                                Text(
                                    text = "$shape",
                                    color = Color.Green,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Bouton de validation
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                if (firstValidationClicked) {
                    // Afficher la boîte de dialogue après la deuxième validation
                    showDialog = true
                } else {
                    // Première validation
                    viewModel.validateAnswers(shapes)
                    firstValidationClicked = true
                }
            }) {
                Text("Validate")
            }
        }
    }

    // Boîte de dialogue pour afficher les résultats (affichée uniquement lors de la deuxième validation)
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Result") },
            text = {
                Text(
                    "Score: $correctCount/${shapes.size}\n" +
                            if (correctCount > 3) "🎉 Congrats, you win!" else "😢 Oops, you failed!"
                )
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onNavigateToGameList()  // Navigation vers la liste des jeux
                }) {
                    Text("Go to Game List")
                }
            }
        )
    }
}
