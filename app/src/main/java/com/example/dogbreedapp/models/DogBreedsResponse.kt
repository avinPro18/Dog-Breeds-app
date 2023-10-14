package com.example.dogbreedapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class DogBreedsResponse : ArrayList<DogBreedsDataItem>()

@Parcelize
data class DogBreedsDataItem(
    val bred_for: String? = "",
    val breed_group: String? = "",
    val country_code: String? = "",
    val description: String? = "",
    val height: Height,
    val history: String? = "",
    val id: Int? = 0,
    val life_span: String? = "",
    val name: String? = "",
    val origin: String? = "",
    val reference_image_id: String? = "",
    var url: String? = "",
    val temperament: String? = "",
    val weight: Weight
): Parcelable

@Parcelize
data class Height(
    val imperial: String? = "",
    val metric: String? = ""
): Parcelable

@Parcelize
data class Weight(
    val imperial: String? = "",
    val metric: String? = ""
): Parcelable