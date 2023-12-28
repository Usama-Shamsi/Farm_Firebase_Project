package com.example.farm_firebase_project

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BirdListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var birdListAdapter: BirdListAdapter
    private lateinit var birdDAO: BirdDAO
    //val backButton: Button = findViewById(R.id.BackListAdd)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_bird)

        recyclerView = findViewById(R.id.bird_fragment_RecyclerView)
        //birdListAdapter = BirdListAdapter()

        birdListAdapter = BirdListAdapter(onDeleteClickListener = ::onDeleteButtonClick)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = birdListAdapter

        birdDAO = BirdDAO(this)
        loadBirdData()


    }
    private fun onDeleteButtonClick(bird: Bird) {
        // Handle the delete action here
        birdDAO.deleteBirdRecord(bird) { success ->
            if (success) {
                // Remove the bird from the adapter after successful deletion
                birdListAdapter.removeBird(bird)
            } else {
                // Handle the case where deletion failed
                Toast.makeText(this, "Failed to delete bird", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadBirdData() {
        birdDAO.getAllBirdRecords { birdRecordList ->
            // Update the adapter with the fetched data
            birdListAdapter.setBirdList(birdRecordList)
        }
    }
}
