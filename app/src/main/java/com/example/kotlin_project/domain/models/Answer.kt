package com.example.kotlin_project.domain.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionId: Int,
    val option: String
)
