package com.example.kotlin_project.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_project.domain.models.Question
import com.example.kotlin_project.domain.models.Answer

@Database(entities = [Question::class, Answer::class], version = 1)
@TypeConverters(Converters::class)  // Indiquer à Room d'utiliser les Converters
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
}
