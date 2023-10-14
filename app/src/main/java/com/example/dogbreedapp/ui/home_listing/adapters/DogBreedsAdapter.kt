package com.example.dogbreedapp.ui.home_listing.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogbreedapp.databinding.DogBreedItemBinding
import com.example.dogbreedapp.models.DogBreedsDataItem

class DogBreedsAdapter(private val clickListener: (DogBreedsDataItem) -> Unit = {}): RecyclerView.Adapter<DogsBreedsViewHolder>() {

    private var dogBreeds = mutableListOf<DogBreedsDataItem>()

    fun setDogBreedList(dogBreeds: List<DogBreedsDataItem>){
        this.dogBreeds = dogBreeds.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsBreedsViewHolder {
        return DogsBreedsViewHolder(DogBreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = dogBreeds.size

    override fun onBindViewHolder(holder: DogsBreedsViewHolder, position: Int) {
        holder.bind(dogBreeds[position], clickListener)
    }
}

class DogsBreedsViewHolder(private val binding: DogBreedItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(dogBreed: DogBreedsDataItem, clickListener: (DogBreedsDataItem) -> Unit){
        binding.apply {
            textViewName.text = dogBreed.name
            textViewBreedOrigin.text = dogBreed.bred_for
            Glide
                .with(this.root.context)
                .load(dogBreed.url)
                .centerCrop()
                .into(imageViewDog)
            root.setOnClickListener {
                clickListener(dogBreed)
            }
        }
    }

}