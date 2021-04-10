package com.example.adaptive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adaptive.workouts.WorkoutsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenWorkouts.setOnClickListener {
            Intent(this, WorkoutsActivity::class.java).also {
                startActivity(it)
            }
        }

        btnStartStopwatch.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
                startActivity(it)
            }
        }

        btnOpenEmomTimer.setOnClickListener {
            Intent(this, EmomActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}