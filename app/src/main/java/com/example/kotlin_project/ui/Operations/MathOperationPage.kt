package com.example.kotlin_project.ui.Operations
import com.example.kotlin_project.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlin_project.viewmodel.CalculationViewModel
import com.example.kotlin_project.domain.models.Calculation // Make sure this import is correct

@Composable
fun MathOperationPage(navController: NavController, operationType: String, viewModel: CalculationViewModel = viewModel()) {
    val calculations by viewModel.calculations.collectAsState()
    val answers = remember { mutableStateListOf(*Array(calculations.size) { "" }) }
    var currentPage by remember { mutableStateOf(0) }
    var validated by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    // Load calculations based on the operation type
    LaunchedEffect(operationType) {
        // Log for debugging
        Log.d("MathOperationPage", "Loading calculations for operation: $operationType")
        viewModel.loadCalculations(operationType)
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            // Use Box to align the CircularProgressIndicator in the center
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) // Show loading indicator
        } else {
            if (calculations.isEmpty()) {
                Text("No calculations available for this operation type.", style = MaterialTheme.typography.bodyMedium)
            } else {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Display calculations and answer fields
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(calculations) { index, calculation ->
                            Text(
                                text = "${calculation.operand1} $operationType ${calculation.operand2}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            BasicTextField(
                                value = answers[index],
                                onValueChange = { answers[index] = it },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color.Gray.copy(alpha = 0.1f))
                                    .padding(16.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            validated = true
                            val score = calculateScore(answers, calculations)
                            navController.navigate("result_screen/$score/Success")
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Validate")
                    }

                    if (validated) {
                        val score = calculateScore(answers, calculations)
                        ResultDialog(score, navController) {
                            // Retry logic or navigate elsewhere
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultDialog(score: Int, navController: NavController, onRetry: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* Dismiss manually handled */ },
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (score >= 10) "Congrats, You Win!" else "Oops! Try Again",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = if (score >= 10) R.drawable.happy else R.drawable.sad),
                    contentDescription = "Result Image",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Score: $score/20",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        confirmButton = {
            Button(onClick = onRetry) { Text("Retry") }
        },
        dismissButton = {
            Button(onClick = { navController.navigate("math_operations") }) { Text("Back to Games") }
        }
    )
}

fun calculateScore(answers: List<String>, calculations: List<Calculation>): Int {
    return answers.zip(calculations) { answer, calculation ->
        // Assuming you're doing addition for the operationType
        if (answer == (calculation.operand1 + calculation.operand2).toString()) 1 else 0
    }.sum()
}
