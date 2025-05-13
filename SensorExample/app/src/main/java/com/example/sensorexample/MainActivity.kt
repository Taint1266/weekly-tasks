package com.example.sensor_nq

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var sensorDataTextView: TextView
    private lateinit var stepCountTextView: TextView
    private var stepCount = 0
    private var previousMagnitude = 0.0
    private val threshold = 1.2  // Sensitivity for detecting steps (adjust as needed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sensorDataTextView = findViewById(R.id.sensorData)
        stepCountTextView=findViewById((R.id.stepCountTextView))
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) {
            sensorDataTextView.text = "Accelerometer not available"
        }

    }
    override fun onResume() {
        super.onResume()
        accelerometer?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            Toast.makeText(this, "Accelerometer sensor registered", Toast.LENGTH_SHORT).show()
        } ?: Toast.makeText(this, "Accelerometer not available", Toast.LENGTH_SHORT).show()
    }


    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0]
            val y = it.values[1]
            val z = it.values[2]
            // Calculate the magnitude of acceleration
            val magnitude = sqrt((x * x) + (y * y) + (z * z))
            // Detect peaks in movement
            if (magnitude - previousMagnitude > threshold) {
                stepCount++
                stepCountTextView.text = "Steps: $stepCount"
            }
            sensorDataTextView.text = "X: $x\nY: $y\nZ: $z"
            previousMagnitude = magnitude.toDouble()
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Typically used for handling calibration or sensor accuracy issues.
        // No action needed for this simple implementation.
    }
}