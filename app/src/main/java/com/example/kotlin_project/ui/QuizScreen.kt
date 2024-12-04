package com.example.kotlin_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_project.viewmodel.QuizViewModel
import com.example.kotlin_project.R

@Composable
fun QuizScreen(navController: NavController, viewModel: QuizViewModel) {
    var questionIndex by remember { mutableStateOf(0) }
    val question = viewModel.questions[questionIndex]
    val progress = (questionIndex + 1) / viewModel.questions.size.toFloat()
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isCorrectAnswer by remember { mutableStateOf<Boolean?>(null) }
    var correctAnswersCount by remember { mutableStateOf(0) }

    // Logique pour calculer le score
    fun handleAnswer(index: Int) {
        if (selectedOptionIndex == null) {
            selectedOptionIndex = index
            isCorrectAnswer = viewModel.checkAnswer(questionIndex, index)
            if (isCorrectAnswer == true) {
                correctAnswersCount++
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Progression et étoile
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .border(2.dp, Color.Green, RoundedCornerShape(10.dp))
                    .padding(2.dp)
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    color = Color.Green,
                    trackColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                )
                if (progress == 1f) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp),
                        tint = Color.Yellow
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Question et options
            Text(
                text = "Question ${questionIndex + 1}",
                color = Color.Blue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = question.text,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Options
            question.options.forEachIndexed { index, option ->
                Button(
                    onClick = { handleAnswer(index) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            selectedOptionIndex == index && isCorrectAnswer == true -> Color.Green
                            selectedOptionIndex == index && isCorrectAnswer == false -> Color.Red
                            else -> Color.LightGray
                        }
                    )
                ) {
                    Text(text = option, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Boutons "Next" et "Go Back" alignés horizontalement
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text("Go Back")
                }

                Button(
                    onClick = {
                        if (questionIndex < viewModel.questions.size - 1) {
                            questionIndex++  // Passe à la question suivante
                            selectedOptionIndex = null  // Réinitialise l'option sélectionnée
                            isCorrectAnswer = null
                        } else {
                            navController.navigate("result_screen/${correctAnswersCount * 100 / viewModel.questions.size}/Réussi")
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    enabled = isCorrectAnswer != null  // Active le bouton seulement si une réponse est donnée
                ) {
                    Text(if (questionIndex < viewModel.questions.size - 1) "Next" else "Finish")
                }
            }

            // Affichage de l'image "exclamation.jpg"
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.exclamation2),
                contentDescription = "Exclamation Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1000.dp)
                    .padding(16.dp)
            )
        }
    }
}
