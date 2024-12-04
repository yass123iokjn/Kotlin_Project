import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlin_project.R

@Composable
fun AlphabetGamePage(navController: NavController, viewModel: AlphabetGameViewModel = viewModel()) {
    val currentLetter = viewModel.currentLetter.value
    val options = viewModel.options.value
    val feedbackState = viewModel.feedbackState.value
    val context = LocalContext.current
    val isAnswerSelected = remember { mutableStateOf(false) }
    val correctAnswers = remember { mutableStateOf(0) }
    val gameFinished = remember { mutableStateOf(false) }

    // Compteur pour limiter les sons à 10
    val soundPlayedCount = remember { mutableStateOf(0) }

    // Mettre à jour le score quand la réponse est correcte
    LaunchedEffect(feedbackState) {
        if (feedbackState == "correct") {
            correctAnswers.value += 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back9),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (!gameFinished.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Question: ${viewModel.questionCount.value}/10", style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))

                // Bouton pour écouter la lettre
                Button(
                    onClick = {
                        if (soundPlayedCount.value < 100) {
                            val resId = context.resources.getIdentifier(
                                "letter_${currentLetter.lowercaseChar()}", "raw", context.packageName
                            )
                            val mediaPlayer = MediaPlayer.create(context, resId)
                            mediaPlayer.start()
                            soundPlayedCount.value += 1
                        }
                    },
                    enabled = soundPlayedCount.value < 100
                ) {
                    Text("🎤 Listen to letter")
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Affichage des options
                options.forEach { letter ->
                    Button(
                        onClick = {
                            viewModel.checkAnswer(letter)
                            isAnswerSelected.value = true // Une fois la réponse sélectionnée, on met l'état à vrai
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(
                                when {
                                    feedbackState == "correct" && letter == currentLetter -> Color.Green
                                    feedbackState == "wrong" && letter == currentLetter -> Color.Green
                                    feedbackState == "wrong" && letter != currentLetter -> Color.Red
                                    else -> Color.White
                                },
                                shape = RoundedCornerShape(20.dp)
                            ),
                        enabled = feedbackState == "default" // Désactiver les boutons après la réponse
                    ) {
                        Text(letter.toString(), style = MaterialTheme.typography.headlineMedium)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (viewModel.questionCount.value >= 10) {
                            gameFinished.value = true
                        } else {
                            viewModel.nextLetter()
                        }
                        isAnswerSelected.value = false
                    },
                    enabled = isAnswerSelected.value
                ) {
                    Text("Next")
                }
            }
        } else {
            // Section des résultats (back10.jpg)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val message = if (correctAnswers.value >= 6) "Congratulations!" else "Try Again!"
                    val imageResource = if (correctAnswers.value >= 6) R.drawable.happy else R.drawable.sad
                    Text("YourScore : ${correctAnswers.value}/10", style = MaterialTheme.typography.bodyLarge)

                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = "Result Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(200.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            viewModel.resetGame()
                            correctAnswers.value = 0
                            gameFinished.value = false
                            soundPlayedCount.value = 0
                        }) {
                            Text("Retry")
                        }

                        Button(
                            onClick = { navController.popBackStack() },
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Text("Go Back")
                        }
                    }
                }
            }
        }
    }
}
