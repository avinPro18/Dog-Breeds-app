package com.example.dogbreedapp.ui.detail

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dogbreedapp.base.BaseActivity
import com.example.dogbreedapp.databinding.ActivityDetailScreenBinding
import com.example.dogbreedapp.models.DogBreedsDataItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : BaseActivity<ActivityDetailScreenBinding, DetailViewModel>() {

    override fun init() {
        setData()
    }

    private fun setData() {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", DogBreedsDataItem::class.java)
        } else {
            intent.getParcelableExtra("data")
        }

        data?.let {dogBreedData ->
            binding.apply {
                textViewName.text = dogBreedData.name ?: ""
                textViewOrigin.text = dogBreedData.bred_for ?: ""
                textViewOtherDetails.text = dogBreedData.temperament ?: ""
                Glide
                    .with(this.root.context)
                    .load(dogBreedData.url)
                    .fitCenter()
                    .into(imageViewDog)
            }
        }
    }

    override fun getBindingLayout(): ActivityDetailScreenBinding {
        return ActivityDetailScreenBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): DetailViewModel {
        return ViewModelProvider(this)[DetailViewModel::class.java]
    }

}