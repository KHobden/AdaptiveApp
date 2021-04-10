package com.example.adaptive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.adaptive.workouts.Workout
import kotlinx.android.synthetic.main.activity_stopwatch.*

class StopwatchActivity : AppCompatActivity() {

    // Initiate chronometer
    var restNumber = 1
    var isStarted = false
    var pausedTime = 0L

    // Input Data
    val restLength = 60*1000L + 10L
    val numSets = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        val workout = intent.getSerializableExtra("EXTRA_WORKOUT") as Workout
        var exerciseList = workout.exerciseList

        tvStopwatchWorkoutName.text = workout.name

        cTimer.base = SystemClock.elapsedRealtime()+restLength

        cTimer.setOnChronometerTickListener {
            var timeRemaining = cTimer.base-SystemClock.elapsedRealtime()
            tvTimeElapsed.text = timeRemaining.toString()
            if(timeRemaining<=0L) {
                cTimer.stop()

                // Increment set number
                restNumber++
                updateSets()

                // Reset timer and stop running
                isStarted = false
                btnStartPause.text = "Start"

                pausedTime = 0L
                cTimer.base = SystemClock.elapsedRealtime()+restLength
            }
        }

        ivAdd.setOnClickListener {
            restNumber++
            updateSets()
        }

        ivMinus.setOnClickListener {
            if(restNumber>1) {
                restNumber--
            }
            updateSets()
        }

        btnStartPause.setOnClickListener {
            if(!isStarted) {
                // Start timing
                isStarted = true
                btnStartPause.text = "Pause"

                // If we have the time on pause is zero, start timer from zero
                // Otherwise tell system our timer started pausedTime seconds ago
                if(pausedTime==0L) {
                    cTimer.base = SystemClock.elapsedRealtime()+restLength
                } else {
                    var intervalOnPause = SystemClock.elapsedRealtime()-pausedTime
                    cTimer.base += intervalOnPause
                }

                cTimer.start()

            } else {
                // Stop timing
                isStarted = false
                btnStartPause.text = "Start"

                pausedTime = SystemClock.elapsedRealtime()
                cTimer.stop()
            }
        }

        btnReset.setOnClickListener {
            if(isStarted) {
                // Reset clicked during timing
                cTimer.stop()
                isStarted = false
                btnStartPause.text = "Start"
            }

            restNumber = 1
            pausedTime = 0L
            cTimer.base = SystemClock.elapsedRealtime()+restLength
        }

    }

    fun updateSets() {
        tvPrev.text = "Set $restNumber"
        tvNext.text = "Set ${restNumber+1}"
    }

}