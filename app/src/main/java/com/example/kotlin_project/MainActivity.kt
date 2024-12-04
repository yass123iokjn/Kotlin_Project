package com.example.kotlin_project

import AlphabetGamePage
import CalculationRepositoryImpl
import SnakeGamePage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

import com.example.kotlin_project.data.local.AnswerDao
import com.example.kotlin_project.data.local.QuestionDao
import com.example.kotlin_project.data.local.QuizDatabase
import com.example.kotlin_project.data.repositories.QuizRepositoryImpl

import com.example.kotlin_project.ui.*
import com.example.kotlin_project.ui.navigation.mathOperationsNav  // Import de la fonction de navigation pour les opérations mathématiques
import com.example.kotlin_project.viewmodel.FormFirstViewModel
import com.example.kotlin_project.viewmodel.QuizViewModel
import com.example.kotlin_project.viewmodel.QuizViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    // Initialisation des ViewModel
    val formFirstViewModel: FormFirstViewModel = viewModel()

    // Création de la base de données et récupération des DAO
    val quizDatabase = Room.databaseBuilder(
        LocalContext.current,
        QuizDatabase::class.java, "quiz-database"
    ).build()

    val questionDao: QuestionDao = quizDatabase.questionDao()
    val answerDao: AnswerDao = quizDatabase.answerDao()

    // Initialisation du QuizRepository et du ViewModel
    val quizRepository = QuizRepositoryImpl(
        questionDao = questionDao,
        answerDao = answerDao
    )

    val quizViewModel: QuizViewModel = viewModel(factory = QuizViewModelFactory(quizRepository))

    // Initialisation du repository pour les opérations mathématiques
    val calculationRepository = CalculationRepositoryImpl()

    // Définir les destinations de la navigation
    NavHost(navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("games_list") { GamesPage(navController) }

        // Ajout d'une route pour FormFirstScreen avec le bon paramètre de navigation
        composable("form_first") {
            FormFirstScreen(
                formFirstViewModel,
                onNavigateToGameList = { navController.navigate("games_list") }
            )
        }

        composable("quiz_screen") { QuizScreen(navController, quizViewModel) }
        composable("result_screen/{score}/{status}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toFloat() ?: 0f
            val status = backStackEntry.arguments?.getString("status") ?: "Échec"
            ResultScreen(navController, score, status)
        }
        composable("alphabet_game") { AlphabetDetailPage(navController) }
        composable("snake_game") { SnakeGamePage(navController) }
        composable("alphabet_quiz") { AlphabetGamePage(navController) }
        composable("math_operations") { MathOpPage(navController) }

        // Routes pour les opérations mathématiques déléguées à une fonction dédiée
        mathOperationsNav(navController, calculationRepository)
    }
}
