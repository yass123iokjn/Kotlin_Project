package com.example.kotlin_project.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Position(val x: Int, val y: Int)

public enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

class SnakeGameViewModel : ViewModel() {
    var snakeBody = mutableStateOf(listOf(Position(5, 5)))
    var direction = mutableStateOf(Direction.RIGHT)
    var foodPosition = mutableStateOf(generateFoodPosition())
    var collectedLetters = mutableStateOf(mutableListOf<Char>())
    var gameOver = mutableStateOf(false)
    var timeLeft = mutableStateOf(300)
    val targetLetter = mutableStateOf('A')

    fun startGame() {
        gameOver.value = false
        snakeBody.value = listOf(Position(5, 5))
        direction.value = Direction.RIGHT
        foodPosition.value = generateFoodPosition()
        collectedLetters.value = mutableListOf()
        timeLeft.value = 300
    }

    suspend fun updateGame() {
        while (timeLeft.value > 0 && !gameOver.value) {
            delay(300) // Speed of the game
            timeLeft.value--
            snakeBody.value = moveSnake(snakeBody.value, direction.value)
            if (checkCollision(snakeBody.value)) {
                gameOver.value = true
            }
            if (checkFoodCollision(snakeBody.value, foodPosition.value)) {
                if (targetLetter.value == 'Z') gameOver.value = true
                collectedLetters.value.add(targetLetter.value)
                if (targetLetter.value < 'Z') targetLetter.value++ // Increment letter
                foodPosition.value = generateFoodPosition()
                snakeBody.value = growSnake(snakeBody.value)
            }
        }
        gameOver.value = true
    }

    fun changeDirection(newDirection: Direction) {
        if (!isOppositeDirection(direction.value, newDirection)) {
            direction.value = newDirection
        }
    }
}

fun generateFoodPosition(): Position {
    return Position(Random.nextInt(0, 20), Random.nextInt(0, 20))
}

fun moveSnake(snakeBody: List<Position>, direction: Direction): List<Position> {
    val head = snakeBody.first()
    val newHead = when (direction) {
        Direction.UP -> Position(head.x, head.y - 1)
        Direction.DOWN -> Position(head.x, head.y + 1)
        Direction.LEFT -> Position(head.x - 1, head.y)
        Direction.RIGHT -> Position(head.x + 1, head.y)
    }
    return listOf(newHead) + snakeBody.dropLast(1)
}

fun growSnake(snakeBody: List<Position>): List<Position> {
    return snakeBody + snakeBody.last()
}

fun checkCollision(snakeBody: List<Position>): Boolean {
    val head = snakeBody.first()
    return head.x < 0 || head.x >= 20 || head.y < 0 || head.y >= 20 || snakeBody.drop(1).contains(head)
}

fun checkFoodCollision(snakeBody: List<Position>, foodPosition: Position): Boolean {
    return snakeBody.first() == foodPosition
}

fun isOppositeDirection(current: Direction, newDir: Direction): Boolean {
    return (current == Direction.UP && newDir == Direction.DOWN) ||
            (current == Direction.DOWN && newDir == Direction.UP) ||
            (current == Direction.LEFT && newDir == Direction.RIGHT) ||
            (current == Direction.RIGHT && newDir == Direction.LEFT)
}
