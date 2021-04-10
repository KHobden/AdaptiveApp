package com.example.adaptive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_emom.*

class EmomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emom)

        var setNumber = 1
        var pausedTime: Long = 0
        var isStarted = false

        cEmomTimer.setOnChronometerTickListener {
            var timeElapsed = SystemClock.elapsedRealtime()-cEmomTimer.base
            if(timeElapsed>=60000L) {
                // Increment set number
                setNumber++
                tvEmomSetNo.text = "Set $setNumber"

                // Reset timer but keep running
                cEmomTimer.base = SystemClock.elapsedRealtime()
                pausedTime = 0L
            }
        }

        btnEmomStartPause.setOnClickListener {
            if(!isStarted) {
                // Start timing
                isStarted = true
                btnEmomStartPause.text = "Pause"

                // If we have the time on pause is zero, start timer from zero
                // Otherwise tell system our timer started pausedTime seconds ago
                if(pausedTime==0L) {
                    cEmomTimer.base = SystemClock.elapsedRealtime()
                } else {
                    var intervalOnPause = SystemClock.elapsedRealtime()-pausedTime
                    cEmomTimer.base += intervalOnPause
                }

                cEmomTimer.start()

            } else {
                // Stop timing
                isStarted = false
                btnEmomStartPause.text = "Start"

                pausedTime = SystemClock.elapsedRealtime()
                cEmomTimer.stop()
            }
        }

        btnEmomReset.setOnClickListener {
            if(isStarted) {
                // Reset clicked during timing
                cEmomTimer.stop()
                isStarted = false
                btnEmomStartPause.text = "Start"
            }

            setNumber = 1
            tvEmomSetNo.text = "Set $setNumber"
            pausedTime = 0L
            cEmomTimer.base = SystemClock.elapsedRealtime()
        }

        ivEmomAdd.setOnClickListener {
            // Reset stopwatch
            // Set number reset
            setNumber++
            tvEmomSetNo.text = "Set $setNumber"
        }

        ivEmomMinus.setOnClickListener {
            if(setNumber>1) {
                setNumber--
            }
            tvEmomSetNo.text = "Set $setNumber"
        }

    }
}