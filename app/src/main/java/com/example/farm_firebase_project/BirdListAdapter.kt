package com.example.farm_firebase_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//----
class BirdListAdapter(private val onDeleteClickListener: (Bird) -> Unit) :
    RecyclerView.Adapter<BirdListAdapter.BirdViewHolder>(){
//class BirdListAdapter : RecyclerView.Adapter<BirdListAdapter.BirdViewHolder>() {

    private var birdList: List<Bird> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirdViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bird, parent, false)
        return BirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: BirdViewHolder, position: Int) {
        val bird = birdList[position]
        holder.bind(bird)

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.invoke(bird)
        }
    }

    override fun getItemCount(): Int = birdList.size

    fun setBirdList(birdList: List<Bird>) {
        this.birdList = birdList
        notifyDataSetChanged()
    }
    fun removeBird(bird: Bird) {
        val updatedList = birdList.toMutableList()
        updatedList.remove(bird)
        birdList = updatedList.toList()
        notifyDataSetChanged()
    }
    inner class BirdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val breedTextView: TextView = itemView.findViewById(R.id.tvBreed)
        private val numberOfBirdsTextView: TextView = itemView.findViewById(R.id.tvNumOfBirds)

        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)


        fun bind(bird: Bird) {
            breedTextView.text = "Breed: ${bird.breed}"
            numberOfBirdsTextView.text = "Number of Birds: ${bird.numberOfBirds}"
        }
    }
}
