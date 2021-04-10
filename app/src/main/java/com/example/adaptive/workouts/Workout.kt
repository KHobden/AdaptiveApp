package com.example.adaptive.workouts

import com.example.adaptive.exercises.Exercise
import java.io.Serializable

data class Workout (
    val name: String,
    val dateLastCompleted: String,
    var exerciseList: List<Exercise>
) : Serializable