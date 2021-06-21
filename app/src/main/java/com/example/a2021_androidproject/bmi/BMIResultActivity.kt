package com.example.a2021_androidproject.bmi
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a2021_androidproject.R
import kotlin.math.pow

class BMIResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_activity_result)

        val key = intent.getIntExtra("key", 0)
        val mugey = intent.getIntExtra("mugey", 0)

        Log.d("ResultActivity", "key : $key, mugey : $mugey")

        val bmi = mugey / (key / 100.0).pow(2.0)
        val resultText = when {
            bmi >=35.0 -> "고도 비만."
            bmi >=30.0 -> "중도 비만."
            bmi >=25.0 -> "경도 비만."
            bmi >=23.0 -> "과체중."
            bmi >=18.5 -> "정상체중."
            else -> "저체중."
        }

        val resultValueTextView = findViewById<TextView>(R.id.bmiResultTextView)
        val resultStringTextView = findViewById<TextView>(R.id.resultTextView)

        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText

    }
}