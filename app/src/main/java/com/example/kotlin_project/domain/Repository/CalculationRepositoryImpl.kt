import com.example.kotlin_project.data.OperationData
import com.example.kotlin_project.domain.Repository.CalculationRepository
import com.example.kotlin_project.domain.models.Calculation

class CalculationRepositoryImpl : CalculationRepository {
    private val data = OperationData()

    override fun getAdditionCalculations(): List<Calculation> =
        data.additionCalculations.map {
            // Extrait les opérandes avant et après l'opérateur
            val operands = it.first.split(" ")
            val operand1 = operands[0].toInt()  // Extrait le premier opérande
            val operand2 = operands[2].toInt()  // Extrait le second opérande
            Calculation(operand1.toString(), operand2.toInt())
        }

    override fun getSubtractionCalculations(): List<Calculation> =
        data.soustractionCalculations.map {
            val operands = it.first.split(" ")
            val operand1 = operands[0].toInt()
            val operand2 = operands[2].toInt()
            Calculation(operand1.toString(), operand2)
        }

    override fun getMultiplicationCalculations(): List<Calculation> =
        data.multiplicationCalculations.map {
            val operands = it.first.split(" ")
            val operand1 = operands[0].toInt()
            val operand2 = operands[2].toInt()
            Calculation(operand1.toString(), operand2)
        }

    override fun getDivisionCalculations(): List<Calculation> =
        data.divisionCalculations.map {
            val operands = it.first.split(" ")
            val operand1 = operands[0].toInt()
            val operand2 = operands[2].toInt()
            Calculation(operand1.toString(), operand2)
        }
}
