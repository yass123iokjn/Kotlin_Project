package com.example.kotlin_project.data

class OperationData {

    // Liste des additions avec des paires (expression, résultat)
    val additionCalculations = listOf(
        Pair("1 + 4 =", 5),
        Pair("2 + 5 =", 7),
        Pair("3 + 3 =", 6),
        Pair("4 + 7 =", 11),
        Pair("5 + 1 =", 6),
        Pair("6 + 2 =", 8),
        Pair("3 + 6 =", 9),
        Pair("4 + 5 =", 9),
        Pair("7 + 3 =", 10),
        Pair("8 + 2 =", 10),
        Pair("2 + 9 =", 11),
        Pair("5 + 4 =", 9),
        Pair("9 + 0 =", 9),
        Pair("1 + 8 =", 9),
        Pair("7 + 2 =", 9),
        Pair("3 + 4 =", 7)
    )

    // Liste des soustractions avec des paires (expression, résultat)
    val soustractionCalculations = listOf(
        Pair("8 - 3 =", 5),
        Pair("7 - 2 =", 5),
        Pair("10 - 4 =", 6),
        Pair("12 - 5 =", 7),
        Pair("9 - 6 =", 3),
        Pair("11 - 3 =", 8),
        Pair("13 - 7 =", 6),
        Pair("10 - 3 =", 7),
        Pair("14 - 6 =", 8),
        Pair("15 - 9 =", 6),
        Pair("18 - 7 =", 11),
        Pair("20 - 12 =", 8),
        Pair("11 - 2 =", 9),
        Pair("17 - 8 =", 9),
        Pair("19 - 14 =", 5),
        Pair("21 - 13 =", 8)
    )

    // Liste des multiplications avec des paires (expression, résultat)
    val multiplicationCalculations = listOf(
        Pair("1 * 5 =", 5),
        Pair("2 * 3 =", 6),
        Pair("3 * 4 =", 12),
        Pair("5 * 5 =", 25),
        Pair("4 * 4 =", 16),
        Pair("6 * 3 =", 18),
        Pair("2 * 6 =", 12),
        Pair("7 * 2 =", 14),
        Pair("3 * 7 =", 21),
        Pair("8 * 1 =", 8),
        Pair("5 * 6 =", 30),
        Pair("4 * 3 =", 12),
        Pair("9 * 2 =", 18),
        Pair("7 * 3 =", 21),
        Pair("10 * 1 =", 10),
        Pair("5 * 4 =", 20)
    )

    // Liste des divisions avec des paires (expression, résultat)
    val divisionCalculations = listOf(
        Pair("10 / 2 =", 5),
        Pair("8 / 4 =", 2),
        Pair("20 / 5 =", 4),
        Pair("18 / 3 =", 6),
        Pair("12 / 6 =", 2),
        Pair("14 / 2 =", 7),
        Pair("16 / 4 =", 4),
        Pair("21 / 3 =", 7),
        Pair("24 / 6 =", 4),
        Pair("30 / 5 =", 6),
        Pair("36 / 6 =", 6),
        Pair("28 / 7 =", 4),
        Pair("42 / 6 =", 7),
        Pair("50 / 10 =", 5),
        Pair("48 / 8 =", 6),
        Pair("40 / 8 =", 5)
    )
}
