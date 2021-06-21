package com.example.a2021_androidproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.a2021_androidproject.bmi.BMIMainActivity
import com.example.a2021_androidproject.bmi.BMIResultActivity
import java.util.*


class StartActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //달력버튼//
        val button1:ImageButton = findViewById(R.id.calendar_btn)
        val intent1 = Intent(this, Calendar::class.java)

        button1.setOnClickListener{
                    startActivity(intent1)
        }
        //검색버튼//
        val button2:ImageButton = findViewById(R.id.search_btn)
        val intent2 = Intent(this, MainActivity::class.java)

        button2.setOnClickListener{
            startActivity(intent2)
        }
        //bmi버튼//
        val button3:ImageButton = findViewById(R.id.bmi_btn)
        val intent3 = Intent(this, BMIMainActivity::class.java)

        button3.setOnClickListener{
            startActivity(intent3)
        }


    }
}

