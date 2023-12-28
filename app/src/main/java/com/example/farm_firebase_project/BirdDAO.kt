package com.example.farm_firebase_project

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

//class BirdDAO(private val activity: AddBird) {
class BirdDAO(private val activity: AppCompatActivity) {
    private val database = FirebaseDatabase.getInstance()
    private val birdsReference: DatabaseReference = database.getReference("birds")

    fun insertBirdRecord(bird: Bird, callback: (Boolean) -> Unit) {
        val keyString = birdsReference.push().key // keyString is a String

        try {
            // Now you can assign the generated key to bird.id
            if (keyString != null) {
                bird.id = keyString
            }

            // Assuming birdsReference is a DatabaseReference
            if (keyString != null) {
                birdsReference.child(keyString)
                    .setValue(bird)
                    .addOnSuccessListener {
                        // Record added successfully
                        callback(true)
                    }
                    .addOnFailureListener {
                        // Handle errors
                        callback(false)
                    }
            }
        } catch (e: Exception) {
            // Handle the case where something went wrong
            callback(false)
        }
    }
fun getAllBirdRecords(callback: (List<Bird>) -> Unit) {
        birdsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val birdRecordList = mutableListOf<Bird>()

                for (birdSnapshot in snapshot.children) {
                    val bird = birdSnapshot.getValue(Bird::class.java)
                    if (bird != null) {
                        birdRecordList.add(bird)
                    }
                }

                callback(birdRecordList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
                callback(emptyList())
            }
        })
    }

    fun deleteBirdRecord(bird: Bird, callback: (Boolean) -> Unit) {
        // Assuming birdsReference is a DatabaseReference
        val birdReference = birdsReference.child(bird.id.toString())

        birdReference.removeValue()
            .addOnSuccessListener {
                // Deletion successful
                callback(true)
            }
            .addOnFailureListener {
                // Deletion failed
                callback(false)
            }
    }


}




















/*fun getAllBirdRecords(callback: (List<Bird>) -> Unit) {
      birdsReference.addListenerForSingleValueEvent(object : ValueEventListener {
          override fun onDataChange(snapshot: DataSnapshot) {
              val birdRecordList = mutableListOf<Bird>()

              for (birdSnapshot in snapshot.children) {
                  val bird = birdSnapshot.getValue(Bird::class.java)
                  if (bird != null) {
                      birdRecordList.add(bird)
                  }
              }

              callback(birdRecordList)
          }

          override fun onCancelled(error: DatabaseError) {
              // Handle errors
              callback(emptyList())
          }
      })
  }*/