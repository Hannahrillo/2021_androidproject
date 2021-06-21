package com.example.a2021_androidproject.bmi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2021_androidproject.R

class BMIMainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.bmi_activity_main)

            val key_EditText: EditText = findViewById(R.id.key_editText)
            val mugey_EditText =findViewById<EditText>(R.id.mugey_editText)

            val resultButton = findViewById<Button>(R.id.result_button)

            resultButton.setOnClickListener{
                Log.d("MainActivity", "ResultButton 이 클릭되었습니다.")

                if (key_EditText.text.isEmpty() || mugey_EditText.text.isEmpty()){
                    Toast.makeText(this,"빈 값이 있습니다", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //이 아래로는 절대 빈 값이 올 수 없음.

                val key :Int = key_EditText.text.toString().toInt()
                val mugey :Int = mugey_EditText.text.toString().toInt()

                val intent = Intent(this, BMIResultActivity::class.java)
                intent.putExtra("key", key )
                intent.putExtra("mugey", mugey)

                startActivity(intent)

            }
        }
    }
