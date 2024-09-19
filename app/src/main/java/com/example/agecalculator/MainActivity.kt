package com.example.agecalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var dobEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var ageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dobEditText = findViewById(R.id.dobEditText)
        calculateButton = findViewById(R.id.calculateButton)
        ageTextView = findViewById(R.id.ageTextView)

        calculateButton.setOnClickListener {
            val dobString = dobEditText.text.toString()
            val age = calculateAge(dobString)
            ageTextView.text = if (age != null) {
                "Your age is: $age years"
            } else {
                "Invalid date format. Please use yyyy-mm-dd."
            }
        }
    }

    private fun calculateAge(dobString: String): Int? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dob = sdf.parse(dobString) ?: return null
            val currentDate = Calendar.getInstance()
            val birthDate = Calendar.getInstance().apply { time = dob }

            var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
            if (currentDate.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (currentDate.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
                        currentDate.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
                age--
            }
            age
        } catch (e: Exception) {
            null
        }
    }
}
