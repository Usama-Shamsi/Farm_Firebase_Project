package com.example.farm_firebase_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nextButton: Button = findViewById(R.id.btnNext)

        nextButton.setOnClickListener {
            // Launch AddBirdActivity
            val intent = Intent(this, BirdMainActivity::class.java)
            startActivity(intent)
        }
    }
}