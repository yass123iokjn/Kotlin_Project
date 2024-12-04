// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    kotlin("jvm") version "1.8.0" // Assurez-vous que cette version est correcte pour votre projet
    kotlin("kapt") version "1.8.0"
}