package com.example.adaptive.workouts

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adaptive.R
import com.example.adaptive.exercises.ExerciseActivity
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutAdapter(
        var workouts: List<Workout>
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{view: View ->
                val workout = workouts[adapterPosition]
                Log.d("OVERVIEW", "Passing ${workouts[adapterPosition].name} from WorkoutAdapter.kt to ExerciseActivity.kt")

                val intent = Intent(view.context, ExerciseActivity::class.java)
                intent.also {
                    it.putExtra("EXTRA_WORKOUT", workout)
                }
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.itemView.apply {
            tvWorkoutName.text = workouts[position].name
            tvDateLastCompleted.text = workouts[position].dateLastCompleted

            if(workouts[position].exerciseList.isEmpty()) {
                tvWorkoutInfo.text = "Exercise 1, Exercise 2, Exercise 3, Exercise 4, Exercise 5, Exercise 6, Exercise 7, Exercise 8"
            } else {
                var info = mutableListOf<String>()
                for(exercise in workouts[position].exerciseList) {
                    info.add(exercise.name)
                }
                tvWorkoutInfo.text = info.joinToString()
            }
        }
    }
}