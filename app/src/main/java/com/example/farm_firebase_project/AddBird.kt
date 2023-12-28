package com.example.farm_firebase_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddBird : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var numberOfBird: EditText
    private lateinit var birdArrivalEditText: EditText
    private lateinit var ageInDaysEditText: EditText
    private lateinit var vaccinationStatusCheck: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bird)

        // Access UI components through findViewById
        idEditText = findViewById(R.id.editTextID)
        breedEditText = findViewById(R.id.editTextBreed)
        numberOfBird = findViewById(R.id.editTextNumberOfBirds)
        birdArrivalEditText = findViewById(R.id.editTextBirdArrival)
        ageInDaysEditText = findViewById(R.id.editTextAgeInDays)
        vaccinationStatusCheck = findViewById(R.id.checkBoxVaccinationStatus)

        val submitButton: Button = findViewById(R.id.btnSubmitFlock)
        val backButton: Button = findViewById(R.id.btnBackAdd)

        submitButton.setOnClickListener {
            // Extract values from UI components
            val id = idEditText.text.toString()
            val breed = breedEditText.text.toString()
            val numberOfBirds = numberOfBird.text.toString().toInt()
            val birdArrivalDate = birdArrivalEditText.text.toString()
            val ageInDays = ageInDaysEditText.text.toString().toInt()
            val vaccinationStatus = vaccinationStatusCheck.isChecked


            // Insert values into Firebase using your DAO class
            //val birdDAO = BirdDAO(this)
            val birdDAO = BirdDAO(this)
            //val birdDAO = BirdDAO(this@BirdAdapterActivity)
            birdDAO.insertBirdRecord(
                Bird(id, breed, numberOfBirds, birdArrivalDate, ageInDays, vaccinationStatus)
            ) { success ->
                if (success) {
                    // After inserting, navigate to the BirdAdapterActivity
                    navigateToBirdMainActivity()
                } else {
                    // Handle the case where insertion failed (show a toast, log, etc.)
                }
            }
        }

        backButton.setOnClickListener {

            finish()
        }
    }

    private fun navigateToBirdMainActivity() {
        val intent = Intent(this, BirdMainActivity::class.java)
        startActivity(intent)
    }
}
