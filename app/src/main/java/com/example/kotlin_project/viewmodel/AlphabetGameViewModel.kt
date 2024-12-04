import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
class AlphabetGameViewModel : ViewModel() {
    private val alphabet = ('A'..'Z').toList()

    private val _currentLetter = mutableStateOf(alphabet.random())
    val currentLetter: State<Char> = _currentLetter

    private val _options = mutableStateOf(generateOptions(_currentLetter.value))
    val options: State<List<Char>> = _options

    private val _feedbackState = mutableStateOf("default") // "default", "correct", "wrong"
    val feedbackState: State<String> = _feedbackState

    private val _questionCount = mutableStateOf(0) // Track number of questions asked
    val questionCount: State<Int> = _questionCount

    // Vérifier la réponse
    fun checkAnswer(selectedLetter: Char) {
        _feedbackState.value = if (selectedLetter == _currentLetter.value) "correct" else "wrong"
    }

    // Passer à la lettre suivante
    fun nextLetter() {
        if (_questionCount.value < 10) {
            _currentLetter.value = alphabet.random()
            _options.value = generateOptions(_currentLetter.value)
            _feedbackState.value = "default"
            _questionCount.value += 1
        }
    }

    // Générer des options
    private fun generateOptions(correctLetter: Char): List<Char> {
        val optionsSet = mutableSetOf(correctLetter)
        while (optionsSet.size < 4) {
            optionsSet.add(alphabet.random())
        }
        return optionsSet.shuffled()
    }

    fun resetGame() {
        _currentLetter.value = 'A'
        _feedbackState.value = "default"
        _options.value = generateOptions(_currentLetter.value)
        _questionCount.value = 0
    }
}
