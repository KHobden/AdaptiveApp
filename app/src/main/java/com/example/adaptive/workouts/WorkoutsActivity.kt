package com.example.adaptive.workouts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaptive.R
import com.example.adaptive.exercises.Exercise
import kotlinx.android.synthetic.main.activity_workouts.*

class WorkoutsActivity : AppCompatActivity() {

    var fbiFitnessTest = mutableListOf<Exercise>(
            Exercise("Sit ups", "3xF"),
            Exercise("Press ups", "3xF"),
            Exercise("Air Squats", "3xF"),
            Exercise("Pull ups", "3xF"),
            Exercise("Run", "1.5 miles")
    )

    var bigShoulderDay = mutableListOf<Exercise>(
            Exercise("Overhead Press", "3x10"),
            Exercise("Push Press", "3x10"),
            Exercise("Behind The Neck Press", "3x10"),
            Exercise("Snatch Press", "3x10"),
            Exercise("Front Raises", "3x10"),
            Exercise("Side Raises", "3x10")
    )

    var navySealTraining = mutableListOf<Exercise>(
            Exercise("Lat Pulldowns", "1x8-12"),
            Exercise("Bench Press", "1x8-12"),
            Exercise("Shoulder Press", "1x8-12"),
            Exercise("Low Rows", "1x8-12"),
            Exercise("Rear Delt Flys", "1x8-12"),
            Exercise("Pec Flys", "1x8-12"),
            Exercise("Preacher Curls", "1x8-12"),
            Exercise("Leg Curls", "1x8-12"),
            Exercise("Leg Press", "1x8-12"),
            Exercise("Trap Deadlifts", "1x8-12")
    )

    var powerliftingWorkout = mutableListOf<Exercise>(
            Exercise("Deadlift", "3x2"),
            Exercise("Bench Press", "3x3"),
            Exercise("Rack Pulls", "3x5"),
            Exercise("Incline Bench Press", "3x10"),
            Exercise("Front Raises", "3x10")
    )

    var generalPhysicalPreparedness = mutableListOf<Exercise>(
            Exercise("Pull Ups", "4xF"),
            Exercise("Sled Pushes", "4 Laps"),
            Exercise("Push Ups", "4x10-15"),
            Exercise("Farmer's Carry", "4 Laps"),
            Exercise("Bench Dips", "4x10-15"),
            Exercise("Box Jumps", "4x10")
    )

    var workoutList = mutableListOf(
            Workout("FBI Fitness Test", "07-Apr", fbiFitnessTest),
            Workout("Big Shoulder Day", "24-Sep", bigShoulderDay),
            Workout("Navy SEAL Training", "03-Dec", navySealTraining),
            Workout("General Physical Preparedness", "21-Mar", generalPhysicalPreparedness),
            Workout("Powerlifting Workout", "18-Aug", powerliftingWorkout)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workouts)

        val adapter = WorkoutAdapter(workoutList)
        rvWorkouts.adapter = adapter
        rvWorkouts.layoutManager = LinearLayoutManager(this)

        btnAddWorkout.setOnClickListener {
            if(etAddWorkout.text.isNotEmpty()) {
                val name = etAddWorkout.text.toString()
                etAddWorkout.setText("")
                it.hideKeyboard()

                // Default list of blank exercises
                val defaultExerciseList = mutableListOf<Exercise>(
                        Exercise("Exercise 1", "Sets/ Reps"),
                        Exercise("Exercise 2", "Sets/ Reps"),
                        Exercise("Exercise 3", "Sets/ Reps"),
                        Exercise("Exercise 4", "Sets/ Reps"),
                        Exercise("Exercise 5", "Sets/ Reps")
                )

                val workout = Workout(name, "07-Apr", defaultExerciseList)
                workoutList.add(workout)
                adapter.notifyItemInserted(workoutList.size-1)
            }
        }

    }

    /*
    private fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }*/

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}