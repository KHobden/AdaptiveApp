package com.example.adaptive.exercises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaptive.R
import com.example.adaptive.StopwatchActivity
import com.example.adaptive.workouts.Workout
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        // Obtain workout passed from WorkoutAdapter
        val workout = intent.getSerializableExtra("EXTRA_WORKOUT") as Workout
        var exerciseList = workout.exerciseList

        tvWorkoutTitle.text = workout.name

        // Pass exerciseList to the recycler view through the ExerciseAdapter
        val adapter = ExerciseAdapter(exerciseList)
        rvExercises.adapter = adapter
        rvExercises.layoutManager = LinearLayoutManager(this)

        // Begin timer
        btnBegin.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
                it.putExtra("EXTRA_WORKOUT", workout)
                Log.d("OVERVIEW", "Passing ${workout.name} from ExerciseActivity.kt to StopwatchActivity.kt")
                startActivity(it)
            }
        }

    }
}