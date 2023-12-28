package com.example.farm_firebase_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class BirdMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bird_main)

        val addButton: Button = findViewById(R.id.btnAddBatch)
        val viewButton: Button = findViewById(R.id.btnViewBatch)
        val backButton: Button = findViewById(R.id.btnFlockBack)

        addButton.setOnClickListener {
                // Launch AddBirdActivity
            val intent = Intent(this, AddBird::class.java)
            startActivity(intent)
        }

        viewButton.setOnClickListener {
                // Launch ViewBirdActivity
            val intent = Intent(this, BirdListActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            // Finish the current activity (close BirdActivity)
            finish()
        }
    }

}

