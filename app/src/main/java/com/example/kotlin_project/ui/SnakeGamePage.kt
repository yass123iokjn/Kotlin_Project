import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kotlin_project.viewmodel.Position
import com.example.kotlin_project.viewmodel.SnakeGameViewModel
import com.example.kotlin_project.R
@Composable
fun SnakeGamePage(navController: NavController) {
    val viewModel: SnakeGameViewModel = viewModel()
    val snakeBody by remember { viewModel.snakeBody }
    val direction by remember { viewModel.direction }
    val foodPosition by remember { viewModel.foodPosition }
    val collectedLetters by remember { viewModel.collectedLetters }
    val gameOver by remember { viewModel.gameOver }
    val timeLeft by remember { viewModel.timeLeft }
    val targetLetter by remember { viewModel.targetLetter }

    LaunchedEffect(key1 = timeLeft, key2 = gameOver) {
        viewModel.updateGame() // Update game state
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (gameOver) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = if (targetLetter > 'Z') R.drawable.happy else R.drawable.sad), contentDescription = null, modifier = Modifier.fillMaxSize().align(Alignment.Center).alpha(0.5f))
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (targetLetter > 'Z') "Congrats, you win!" else "Fail, retry!",
                        color = Color.Red,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(onClick = { navController.popBackStack() }) {
                            Text("Go Back")
                        }
                        Button(onClick = { navController.navigate("snake_game") }) {
                            Text("Retry")
                        }
                    }
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                LinearProgressIndicator(
                    progress = timeLeft / 60f,
                    modifier = Modifier.fillMaxWidth().height(10.dp)
                )
                Text(
                    text = "Time Left : ${"%02d".format(timeLeft / 60)}:${"%02d".format(timeLeft % 60)}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                GridDisplay(snakeBody, foodPosition, targetLetter)
                Text(
                    text = "Collected Letters : ${collectedLetters.joinToString(" ")}",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                ControlButtons { newDirection -> viewModel.changeDirection(newDirection) }
            }
        }
    }
}

@Composable
fun GridDisplay(snakeBody: List<Position>, foodPosition: Position, targetLetter: Char) {
    val gridSize = 20
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        repeat(gridSize) { y ->
            Row {
                repeat(gridSize) { x ->
                    val isSnake = snakeBody.any { it.x == x && it.y == y }
                    val isFood = foodPosition.x == x && foodPosition.y == y
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                if (isSnake) Color.Green else if (isFood) Color.Transparent else Color.Gray,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isFood) {
                            Text(
                                text = targetLetter.toString(),
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ControlButtons(onDirectionChange: (com.example.kotlin_project.viewmodel.Direction) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { onDirectionChange(com.example.kotlin_project.viewmodel.Direction.UP) },
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("↑", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onDirectionChange(com.example.kotlin_project.viewmodel.Direction.LEFT) },
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, CircleShape),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("←", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onDirectionChange(com.example.kotlin_project.viewmodel.Direction.RIGHT) },
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, CircleShape),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("→", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                }
            }
            Button(
                onClick = { onDirectionChange(com.example.kotlin_project.viewmodel.Direction.DOWN) },
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, CircleShape),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("↓", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            }
        }
    }
}
