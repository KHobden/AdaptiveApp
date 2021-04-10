package com.example.adaptive.exercises

import java.io.Serializable

data class Exercise (
    val name: String,
    val setsReps: String
) : Serializable