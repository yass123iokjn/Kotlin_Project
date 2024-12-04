package com.example.kotlin_project.data.local

import androidx.room.TypeConverter

class Converters {
    // Convertir une liste de chaînes en une chaîne JSON
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(separator = ",") // Sépare les éléments par une virgule
    }

    // Convertir une chaîne JSON en une liste de chaînes
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",") // Divise la chaîne en fonction de la virgule pour reconstituer la liste
    }
}
